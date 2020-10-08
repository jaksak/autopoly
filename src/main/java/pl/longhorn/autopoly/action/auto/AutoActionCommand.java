package pl.longhorn.autopoly.action.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.action.result.ActionResultProcessor;
import pl.longhorn.autopoly.board.BoardAccessor;
import pl.longhorn.autopoly.board.cqrs.DeleteBoardCommand;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.board.event.cqrs.BoardEventsQuery;
import pl.longhorn.autopoly.board.event.cqrs.DeleteBoardEventCommand;
import pl.longhorn.autopoly.district.field.FieldQuery;
import pl.longhorn.autopoly.district.field.HouseLvlCommand;
import pl.longhorn.autopoly.district.field.LockFieldCommand;
import pl.longhorn.autopoly.district.field.housable.HousableField;
import pl.longhorn.autopoly.district.field.lockable.LockableField;
import pl.longhorn.autopoly.district.player.PlayerDistrictCommand;
import pl.longhorn.autopoly.player.NextPlayerQuery;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayerInBoardQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;
import pl.longhorn.autopoly.turn.FinishTurnCommand;

@Service
@RequiredArgsConstructor
public class AutoActionCommand {

    // TODO: too much responsibility
    private final BoardAccessor boardAccessor;
    private final PlayerInBoardQuery playerInBoardQuery;
    private final NextPlayerQuery nextPlayerQuery;
    private final FinishTurnCommand finishTurnCommand;
    private final ActionResultProcessor actionResultProcessor;
    private final DeleteBoardEventCommand deleteBoardEventCommand;
    private final DeleteBoardCommand deleteBoardCommand;
    private final BoardEventsQuery boardEventsQuery;
    private final LockFieldCommand lockFieldCommand;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldQuery fieldQuery;
    private final PlayerDistrictCommand playerDistrictCommand;
    private final HouseLvlCommand houseLvlCommand;

    public boolean doAutoAction() {
        boolean shouldContinue = performPossibleEvents();
        if (shouldContinue) {
            shouldContinue = performSingleAction();
            if (shouldContinue) {
                shouldContinue = performPossibleEvents();
            }
            unlockAnyField();
            buildHouse();
            finishTurnCommand.finish();
        }
        return shouldContinue;
    }

    private boolean performSingleAction() {
        var currentPlayer = nextPlayerQuery.get();
        if (currentPlayer.isPresent()) {
            return performSingleActionInternal(currentPlayer.get());
        } else {
            deleteBoardCommand.deleteBoard();
            return false;
        }
    }

    private boolean performSingleActionInternal(Player currentPlayer) {
        boolean shouldContinue = true;
        if (currentPlayer.shouldUseAutoAction()) {
            var actionResult = currentPlayer.getState().autoProcessAction(currentPlayer);
            actionResultProcessor.processResult(actionResult);
        } else {
            shouldContinue = false;
        }
        return shouldContinue;
    }

    private boolean performPossibleEvents() {
        var shouldContinue = true;
        var board = boardAccessor.getBoard();
        var playerInBoard = playerInBoardQuery.get();
        for (BoardEvent event : boardEventsQuery.get()) {
            Player player = playerInBoard.getPlayerById(event.getPlayerId());
            if (player.shouldUseAutoAction()) {
                var result = event.react(board, player);
                actionResultProcessor.processResult(result);
                deleteBoardEventCommand.delete(event.getId());
            } else {
                shouldContinue = false;
            }
        }
        return shouldContinue;
    }

    private void unlockAnyField() {
        var optionalPlayer = nextPlayerQuery.get();
        if (optionalPlayer.isPresent()) {
            var player = optionalPlayer.get();
            var playerFieldIds = playerOwnershipQuery.get(player.getId());
            playerFieldIds.stream()
                    .map(fieldQuery::getField)
                    .filter(field -> field instanceof LockableField)
                    .map(field -> (LockableField) field)
                    .filter(LockableField::isLocked)
                    .filter(field -> player.getMoneyAmount() > field.getLockPrice())
                    .findAny()
                    .ifPresent(field -> lockFieldCommand.unlock(field.getId(), player.getId()));
        }
    }

    private void buildHouse() {
        var optionalPlayer = nextPlayerQuery.get();
        if (optionalPlayer.isPresent()) {
            var player = optionalPlayer.get();
            var playerDistricts = playerDistrictCommand.prepare(player.getId());
            playerDistricts.getFull().stream()
                    .flatMap(district -> district.getOwnedFieldIds().stream())
                    .map(fieldQuery::getField)
                    .filter(field -> field instanceof HousableField)
                    .map(field -> (HousableField) field)
                    .filter(field -> player.getMoneyAmount() > field.getCurrentHousePrice())
                    .filter(HousableField::shouldIncreaseHouseLvl)
                    .findAny()
                    .ifPresent(field -> houseLvlCommand.increaseLvl(field.getId(), player.getId()));
        }
    }
}

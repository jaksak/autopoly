package pl.longhorn.autopoly.action.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.action.result.ActionResultProcessor;
import pl.longhorn.autopoly.board.BoardAccessor;
import pl.longhorn.autopoly.board.cqrs.DeleteBoardCommand;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.board.event.cqrs.BoardEventsQuery;
import pl.longhorn.autopoly.board.event.cqrs.DeleteBoardEventCommand;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.cqrs.*;
import pl.longhorn.autopoly.district.player.PlayerDistrict;
import pl.longhorn.autopoly.district.player.PlayerDistrictCommand;
import pl.longhorn.autopoly.district.player.PlayerDistricts;
import pl.longhorn.autopoly.player.NextPlayerQuery;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayerInBoardQuery;
import pl.longhorn.autopoly.player.PlayerOwnershipAccessor;
import pl.longhorn.autopoly.turn.FinishTurnCommand;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    private final FieldQuery fieldQuery;
    private final PlayerOwnershipAccessor playerOwnershipAccessor;
    private final PlayerDistrictCommand playerDistrictCommand;
    private final HouseLvlCommand houseLvlCommand;
    private final HouseFieldPolicyQuery houseFieldPolicyQuery;
    private final Randomizer randomizer;
    private final LockFieldPolicyQuery lockFieldPolicyQuery;

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
            getFieldToUnlock(player).ifPresent(field -> lockFieldCommand.unlock(field.getId(), player.getId()));
        }
    }

    private Optional<AutopolyField> getFieldToUnlock(Player player) {
        List<AutopolyField> fieldsToUnlock = new LinkedList<>();
        for (var field : playerOwnershipAccessor.get(player.getId())) {
            var lockPolicy = lockFieldPolicyQuery.get(field);
            if (lockPolicy.shouldUnlock(field) && player.getMoneyAmount() > lockPolicy.getLockPrice(field)) {
                fieldsToUnlock.add(field);
            }
        }
        return randomizer.getRandom(fieldsToUnlock);
    }

    private void buildHouse() {
        var optionalPlayer = nextPlayerQuery.get();
        if (optionalPlayer.isPresent()) {
            var player = optionalPlayer.get();
            var playerDistricts = playerDistrictCommand.prepare(player.getId());
            List<AutopolyField> availableFieldToBuildHouse = getAvailableFieldToBuildHouse(playerDistricts, player);
            randomizer.getRandom(availableFieldToBuildHouse).ifPresent(field -> houseLvlCommand.increaseLvl(field.getId(), player.getId()));
        }
    }

    private List<AutopolyField> getAvailableFieldToBuildHouse(PlayerDistricts playerDistricts, Player player) {
        List<AutopolyField> availableFieldToBuildHouse = new LinkedList<>();
        for (PlayerDistrict playerDistrict : playerDistricts.getFull()) {
            for (String fieldId : playerDistrict.getOwnedFieldIds()) {
                AutopolyField field = fieldQuery.get(fieldId);
                var housePolicy = houseFieldPolicyQuery.get(field);
                if (housePolicy.shouldIncreaseHouseLvl(field) && player.getMoneyAmount() > housePolicy.getCurrentHousePrice(field)) {
                    availableFieldToBuildHouse.add(field);
                }
            }
        }
        return availableFieldToBuildHouse;
    }
}

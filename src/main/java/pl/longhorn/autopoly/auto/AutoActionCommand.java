package pl.longhorn.autopoly.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.action.ActionResultProcessor;
import pl.longhorn.autopoly.board.BoardAccessor;
import pl.longhorn.autopoly.board.DeleteBoardEventCommand;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.player.NextPlayerCommand;
import pl.longhorn.autopoly.player.NextPlayerQuery;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayerInBoardQuery;

@Service
@RequiredArgsConstructor
public class AutoActionCommand {

    private final BoardAccessor boardAccessor;
    private final PlayerInBoardQuery playerInBoardQuery;
    private final NextPlayerQuery nextPlayerQuery;
    private final NextPlayerCommand nextPlayerCommand;
    private final ActionResultProcessor actionResultProcessor;
    private final DeleteBoardEventCommand deleteBoardEventCommand;

    public boolean doAutoAction() {
        boolean shouldContinue = performPossibleEvents();
        if (shouldContinue) {
            shouldContinue = performSingleAction();
            if (shouldContinue) {
                shouldContinue = performPossibleEvents();
            }
            nextPlayerCommand.moveActionToNext();
        }
        return shouldContinue;
    }

    private boolean performSingleAction() {
        var currentPlayer = nextPlayerQuery.get();
        var shouldContinue = true;
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
        for (BoardEvent event : board.getUnconsideredEvents()) {
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


}

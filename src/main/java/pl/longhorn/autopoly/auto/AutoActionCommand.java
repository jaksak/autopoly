package pl.longhorn.autopoly.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.AddBoardEventsCommand;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.BoardAccessor;
import pl.longhorn.autopoly.board.BoardActionResult;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.player.*;

@Service
@RequiredArgsConstructor
public class AutoActionCommand {

    private final BoardAccessor boardAccessor;
    private final PlayerInBoardQuery playerInBoardQuery;
    private final NextPlayerQuery nextPlayerQuery;
    private final DistrictDetailsQuery districtDetailsQuery;
    private final NextPlayerCommand nextPlayerCommand;
    private final BoardLogCommand boardLogCommand;
    private final AddBoardEventsCommand addBoardEventsCommand;

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
        var board = boardAccessor.getBoard();
        var currentPlayer = nextPlayerQuery.get();
        var shouldContinue = true;
        if (currentPlayer.shouldUseAutoAction()) {
            var param = getParam(board);
            var actionResult = currentPlayer.getState().autoProcessAction(param, currentPlayer);
            processResult(actionResult, board);
        } else {
            shouldContinue = false;
        }
        return shouldContinue;
    }

    private AutoProcessActionParam getParam(Board board) {
        return AutoProcessActionParam.builder()
                .board(board)
                .districtDetails(districtDetailsQuery.get())
                .build();
    }

    private boolean performPossibleEvents() {
        var shouldContinue = true;
        var board = boardAccessor.getBoard();
        var playerInBoard = playerInBoardQuery.get();
        for (BoardEvent event : board.getUnconsideredEvents()) {
            Player player = playerInBoard.getPlayerById(event.getPlayerId());
            if (player.shouldUseAutoAction()) {
                var result = event.react(board, player);
                processResult(result, board);
            } else {
                shouldContinue = false;
            }
        }
        return shouldContinue;
    }

    private void processResult(BoardActionResult actionResult, Board board) {
        actionResult.getLogs().forEach(log -> boardLogCommand.add(log, board.getId()));
        if (!actionResult.getEvents().isEmpty()) {
            addBoardEventsCommand.add(actionResult.getEvents());
        }
    }
}

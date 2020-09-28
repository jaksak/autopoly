package pl.longhorn.autopoly.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.*;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.player.AutoProcessActionParam;
import pl.longhorn.autopoly.player.NextPlayerQuery;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayerInBoardQuery;
import pl.longhorn.autopoly.randomizer.Randomizer;

@Service
@RequiredArgsConstructor
public class CheckStateCommand {

    private static final int MAX_ACTION_AMOUNT = 15;

    private final BoardQuery boardQuery;
    private final BoardCommand boardCommand;
    private final NextPlayerQuery nextPlayerQuery;
    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayerInBoardQuery playerInBoardQuery;
    private final Randomizer randomizer;

    public synchronized void checkState() {
        // TODO: checking date!
        boolean shouldContinue = true;
        for (int i = 0; i < MAX_ACTION_AMOUNT && shouldContinue; i++) {
            shouldContinue = performPossibleEvents();
            if (shouldContinue) {
                shouldContinue = performSingleAction();
                if (shouldContinue) {
                    performPossibleEvents();
                }
            }
        }
    }

    private boolean performSingleAction() {
        var board = getBoard();
        var currentPlayer = nextPlayerQuery.get();
        var shouldContinue = true;
        if (currentPlayer.shouldUseAutoAction()) {
            var param = getParam(board);
            var actionResult = currentPlayer.getState().autoProcessAction(param, currentPlayer);
            processResult(actionResult);
        } else {
            shouldContinue = false;
        }
        return shouldContinue;
    }

    private boolean performPossibleEvents() {
        var shouldContinue = true;
        var board = getBoard();
        var playerInBoard = playerInBoardQuery.get();
        for (BoardEvent event : board.getUnconsideredEvents()) {
            Player player = playerInBoard.getPlayerById(event.getPlayerId());
            if (player.shouldUseAutoAction()) {
                var result = event.react(board, player);
                processResult(result);
            } else {
                shouldContinue = false;
            }
        }
        return shouldContinue;
    }

    private void processResult(BoardActionResult actionResult) {

    }

    private AutoProcessActionParam getParam(Board board) {
        return AutoProcessActionParam.builder()
                .board(board)
                .districtDetails(districtDetailsQuery.get())
                .randomizer(randomizer)
                .build();
    }

    private Board getBoard() {
        var result = boardQuery.get();
        if (result == null) {
            return boardCommand.create();
        } else {
            return result;
        }
    }
}

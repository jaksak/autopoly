package pl.longhorn.autopoly.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.BoardCommand;
import pl.longhorn.autopoly.board.BoardQuery;

@Service
@RequiredArgsConstructor
public class CheckStateCommand {

    private static final int MAX_ACTION_AMOUNT = 15;

    private final BoardQuery boardQuery;
    private final BoardCommand boardCommand;

    public synchronized void checkState() {
        // TODO: checking date!

        for (int i = 0; i < MAX_ACTION_AMOUNT; i++) {
            var param = getParam();

        }
    }

    private CheckStateInternalParam getParam() {
        return CheckStateInternalParam.builder()
                .board(getBoard())
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

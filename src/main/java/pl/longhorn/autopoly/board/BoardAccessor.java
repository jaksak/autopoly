package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardCommand;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;

@Service
@RequiredArgsConstructor
public class BoardAccessor {

    private final BoardQuery boardQuery;
    private final BoardCommand boardCommand;

    public Board getBoard() {
        var result = boardQuery.get();
        if (result == null) {
            return boardCommand.create();
        } else {
            return result;
        }
    }
}

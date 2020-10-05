package pl.longhorn.autopoly.board.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.BoardListener;
import pl.longhorn.autopoly.board.BoardRepository;
import pl.longhorn.autopoly.util.id.IdFactory;

@Service
@RequiredArgsConstructor
public class BoardCommand {

    private final BoardRepository repository;
    private final BoardListener listener;
    private final IdFactory idFactory;

    public Board create() {
        Board board = Board.builder()
                .id(idFactory.generate())
                .build();
        repository.save(board);
        listener.afterBoardCreate();
        return board;
    }
}

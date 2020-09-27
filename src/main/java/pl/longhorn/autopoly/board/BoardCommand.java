package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.id.IdFactory;

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

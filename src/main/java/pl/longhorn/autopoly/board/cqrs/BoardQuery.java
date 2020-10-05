package pl.longhorn.autopoly.board.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardQuery {

    private final BoardRepository repository;

    public Board get() {
        return repository.get();
    }
}

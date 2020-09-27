package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardQuery {

    private final BoardRepository repository;

    public Board get() {
        return repository.get();
    }
}

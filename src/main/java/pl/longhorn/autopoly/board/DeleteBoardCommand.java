package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBoardCommand {

    private final BoardRepository repository;
    private final BoardListener boardListener;

    public void deleteBoard() {
        repository.save(null);
        boardListener.afterBoardDelete();
    }
}

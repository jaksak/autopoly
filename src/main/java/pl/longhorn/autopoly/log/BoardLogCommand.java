package pl.longhorn.autopoly.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.id.IdFactory;

@Service
@RequiredArgsConstructor
public class BoardLogCommand {

    private final int MAX_LOG_AMOUNT = 100;

    private final BoardLogRepository repository;
    private final IdFactory idFactory;

    public void add(BoardLogContent view, String boardId) {
        BoardLog log = new BoardLog(idFactory.generate(), boardId, view);
        var logs = repository.getAll();
        if (logs.size() > MAX_LOG_AMOUNT) {
            logs.remove();
        }
        logs.add(log);
        repository.replace(logs);
    }
}

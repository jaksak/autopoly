package pl.longhorn.autopoly.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardLogQuery {

    private final BoardLogRepository repository;

    public List<BoardLog> getByBoardId(String boardId) {
        return repository.getAll()
                .stream()
                .filter(log -> boardId.equals(log.getBoardId()))
                .collect(Collectors.toList());
    }
}

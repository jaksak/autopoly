package pl.longhorn.autopoly.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardLogAfterIdQuery {

    private final BoardLogQuery boardLogQuery;

    public List<BoardLog> getLogByBoardIdAndAfter(String boardId, String afterId) {
        var logsFromBoard = boardLogQuery.getByBoardId(boardId);
        var logsAfterId = getLogsAfterId(logsFromBoard, afterId);
        if (logsAfterId.isEmpty()) {
            return logsFromBoard;
        } else {
            return logsAfterId;
        }
    }

    private List<BoardLog> getLogsAfterId(List<BoardLog> logs, String afterId) {
        List<BoardLog> toReturn = new LinkedList<>();
        boolean idExceed = false;
        for (BoardLog log : logs) {
            if (idExceed) {
                toReturn.add(log);
            } else if (afterId.equals(log.getId())) {
                idExceed = true;
            }
        }
        return toReturn;
    }
}

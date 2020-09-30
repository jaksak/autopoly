package pl.longhorn.autopoly.log;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.autopoly.log.content.BoardLogContent;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BoardLog {
    private final String id;
    private final String boardId;
    BoardLogContent content;

    public BoardLogView toView() {
        return new BoardLogView(id, content);
    }
}

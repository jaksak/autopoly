package pl.longhorn.autopoly.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.log.content.BoardLogContent;

import java.util.List;

@Getter
@Builder
public class BoardActionResult {
    @Singular
    private final List<BoardLogContent> logs;

    @Singular
    private final List<BoardEvent> events;
}

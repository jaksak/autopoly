package pl.longhorn.autopoly.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.district.FieldOwnerityChange;
import pl.longhorn.autopoly.log.content.BoardLogContent;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class BoardActionResult {
    @Singular
    private final List<BoardLogContent> logs;

    @Singular
    private final List<BoardEvent> events;

    @Singular
    private final List<FieldOwnerityChange> fieldOwnerityChanges;

    private final int moneyChange;
}

package pl.longhorn.autopoly.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.district.ownership.FieldOwnershipChange;
import pl.longhorn.autopoly.district.ownership.MoneyChange;
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
    private final List<FieldOwnershipChange> fieldOwnershipChanges;

    @Singular
    private final List<MoneyChange> moneyChanges;
}

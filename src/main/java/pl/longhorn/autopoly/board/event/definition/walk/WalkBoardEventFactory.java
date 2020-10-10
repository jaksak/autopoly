package pl.longhorn.autopoly.board.event.definition.walk;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.cqrs.ActionFieldPolicyQuery;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.player.UpdatePlayerPositionCommand;
import pl.longhorn.autopoly.util.id.IdFactory;

@Component
@RequiredArgsConstructor
public class WalkBoardEventFactory {
    private final FieldQuery fieldQuery;
    private final UpdatePlayerPositionCommand updatePlayerPositionCommand;
    private final IdFactory idFactory;
    private final ActionFieldPolicyQuery actionFieldPolicyQuery;

    public WalkBoardEvent create(String playerId, String fieldId) {
        return new WalkBoardEvent(idFactory.generate(), playerId, fieldId, fieldQuery, updatePlayerPositionCommand, actionFieldPolicyQuery);
    }
}

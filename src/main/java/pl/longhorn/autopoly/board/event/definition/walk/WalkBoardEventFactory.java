package pl.longhorn.autopoly.board.event.definition.walk;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.FieldQuery;
import pl.longhorn.autopoly.player.UpdatePlayerPositionCommand;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.util.id.IdFactory;

@Component
@RequiredArgsConstructor
public class WalkBoardEventFactory {
    private final FieldQuery fieldQuery;
    private final UpdatePlayerPositionCommand updatePlayerPositionCommand;
    private final FieldOwnershipQuery fieldOwnershipQuery;
    private final IdFactory idFactory;

    public WalkBoardEvent create(String playerId, String fieldId) {
        return new WalkBoardEvent(idFactory.generate(), playerId, fieldId, fieldQuery, updatePlayerPositionCommand, fieldOwnershipQuery);
    }
}

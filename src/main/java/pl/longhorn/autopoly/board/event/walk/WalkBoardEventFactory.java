package pl.longhorn.autopoly.board.event.walk;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.FieldQuery;
import pl.longhorn.autopoly.player.PlayerInBoardQuery;
import pl.longhorn.autopoly.player.UpdatePlayerPositionCommand;

@Component
@RequiredArgsConstructor
public class WalkBoardEventFactory {
    private final FieldQuery fieldQuery;
    private final UpdatePlayerPositionCommand updatePlayerPositionCommand;
    private final PlayerInBoardQuery playerInBoardQuery;

    public WalkBoardEvent create(String playerId, String fieldId) {
        return new WalkBoardEvent(playerId, fieldId, fieldQuery, updatePlayerPositionCommand, playerInBoardQuery);
    }
}

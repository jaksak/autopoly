package pl.longhorn.autopoly.log.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerMoveLogContent implements BoardLogContent {
    private final String id;
    private final BoardLogType type = BoardLogType.PLAYER_MOVE;
    private final String playerId;
    private final String currentPosition;
    private final PlayerMoveReason reason;
}
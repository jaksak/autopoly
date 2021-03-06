package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerWalkLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.PLAYER_MOVE;
    private final String playerId;
    private final String fieldId;
}

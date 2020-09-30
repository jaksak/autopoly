package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerWalkLogContent implements BoardLogContent {
    private final String id;
    private final BoardLogType type = BoardLogType.PLAYER_MOVE;
    private final String fieldId;
}

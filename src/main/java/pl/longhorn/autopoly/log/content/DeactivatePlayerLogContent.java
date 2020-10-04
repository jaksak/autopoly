package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeactivatePlayerLogContent implements BoardLogContent {

    private final BoardLogType type = BoardLogType.DEACTIVE_PLAYER;
    private final String playerId;
}

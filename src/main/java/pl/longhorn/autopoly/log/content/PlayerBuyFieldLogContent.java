package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerBuyFieldLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.PLAYER_BUY;
    private final String playerId;
    private final String fieldId;
}

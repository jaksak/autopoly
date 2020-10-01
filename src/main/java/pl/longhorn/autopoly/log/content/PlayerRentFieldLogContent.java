package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerRentFieldLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.PLAYER_RENT;
    private final String playerId;
    private final String fieldId;
    private final int price;
}

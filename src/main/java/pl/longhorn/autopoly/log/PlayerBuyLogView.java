package pl.longhorn.autopoly.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerBuyLogView implements BoardLogView {

    private final String id;
    private final String playerId;
    private final String propertyId;

    @Override
    public BoardLogType getType() {
        return BoardLogType.PLAYER_BUY;
    }
}

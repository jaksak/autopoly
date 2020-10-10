package pl.longhorn.autopoly.log.content;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.trade.TradePropositionView;

@Getter
@Builder
public class TradeFinishedLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.TRADE_FINISHED;
    private final String id;
    private final boolean success;
    private final TradePropositionView proposition;
}

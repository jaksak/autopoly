package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.trade.TradePropositionView;

@Getter
@RequiredArgsConstructor
public class TradeStartedLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.TRADE_STARTED;
    private final String id;
    private final TradePropositionView proposition;
}

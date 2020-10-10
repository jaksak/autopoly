package pl.longhorn.autopoly.trade;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TradePropositionView {
    private final String player1Id;
    private final List<String> player1Fields;
    private final int player1Money;
    private final String player2Id;
    private final List<String> player2Fields;
    private final int player2Money;
}

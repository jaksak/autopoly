package pl.longhorn.autopoly.trade;

import org.springframework.stereotype.Component;

@Component
public class TradePropositionViewFactory {

    public TradePropositionView toView(TradeProposition tradeProposition) {
        return TradePropositionView.builder()
                .player1Id(tradeProposition.getOffer1().getPlayer())
                .player1Fields(tradeProposition.getOffer1().getFields())
                .player1Money(tradeProposition.getOffer1().getMoney())
                .player2Id(tradeProposition.getOffer2().getPlayer())
                .player2Fields(tradeProposition.getOffer2().getFields())
                .player2Money(tradeProposition.getOffer2().getMoney())
                .build();
    }
}

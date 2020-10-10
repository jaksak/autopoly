package pl.longhorn.autopoly.trade.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.TradeFinishedLogContent;
import pl.longhorn.autopoly.player.UpdateMoneyCommand;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipUpdateCommand;
import pl.longhorn.autopoly.trade.Offer;
import pl.longhorn.autopoly.trade.TradeProposition;
import pl.longhorn.autopoly.trade.TradePropositionRepository;
import pl.longhorn.autopoly.trade.TradePropositionViewFactory;

@Service
@RequiredArgsConstructor
public class RunTradePropositionCommand {

    private final TradePropositionRepository tradePropositionRepository;
    private final FieldOwnershipUpdateCommand fieldOwnershipUpdateCommand;
    private final UpdateMoneyCommand updateMoneyCommand;
    private final BoardQuery boardQuery;
    private final BoardLogCommand boardLogCommand;
    private final TradePropositionViewFactory tradePropositionViewFactory;

    public void run() {
        var proposition = tradePropositionRepository.get();
        var player1Id = proposition.getOffer1().getPlayer();
        var player2Id = proposition.getOffer2().getPlayer();
        runOffer(proposition.getOffer1(), player2Id);
        runOffer(proposition.getOffer2(), player1Id);
        addLog(proposition);
    }

    private void addLog(TradeProposition proposition) {
        boardLogCommand.add(
                TradeFinishedLogContent.builder()
                        .id(proposition.getId())
                        .proposition(tradePropositionViewFactory.toView(proposition))
                        .success(true)
                        .build(),
                boardQuery.get().getId());
    }

    private void runOffer(Offer offer, String targetPlayerId) {
        offer.getFields().forEach(field -> fieldOwnershipUpdateCommand.changeOwnership(targetPlayerId, field));
        updateMoneyCommand.updateMoney(offer.getPlayer(), -offer.getMoney());
        updateMoneyCommand.updateMoney(targetPlayerId, offer.getMoney());
    }
}

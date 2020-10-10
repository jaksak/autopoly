package pl.longhorn.autopoly.trade.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.TradeFinishedLogContent;
import pl.longhorn.autopoly.player.UpdateMoneyCommand;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipUpdateCommand;
import pl.longhorn.autopoly.trade.TradeProposition;
import pl.longhorn.autopoly.trade.TradePropositionRepository;
import pl.longhorn.autopoly.trade.TradePropositionViewFactory;

@Service
@RequiredArgsConstructor
public class ClearTradePropositionCommand {

    private final TradePropositionRepository tradePropositionRepository;
    private final FieldOwnershipUpdateCommand fieldOwnershipUpdateCommand;
    private final UpdateMoneyCommand updateMoneyCommand;
    private final BoardQuery boardQuery;
    private final BoardLogCommand boardLogCommand;
    private final TradePropositionViewFactory tradePropositionViewFactory;

    public void run() {
        var proposition = tradePropositionRepository.get();
        tradePropositionRepository.clear();
        addLog(proposition);
    }

    private void addLog(TradeProposition proposition) {
        boardLogCommand.add(
                TradeFinishedLogContent.builder()
                        .id(proposition.getId())
                        .proposition(tradePropositionViewFactory.toView(proposition))
                        .success(false)
                        .build(),
                boardQuery.get().getId());
    }
}

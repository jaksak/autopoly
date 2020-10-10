package pl.longhorn.autopoly.trade.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.TradeStartedLogContent;
import pl.longhorn.autopoly.trade.TradeProposition;
import pl.longhorn.autopoly.trade.TradePropositionRepository;
import pl.longhorn.autopoly.trade.TradePropositionViewFactory;

@Service
@RequiredArgsConstructor
public class TradePropositionCommand {

    private final TradePropositionRepository tradePropositionRepository;
    private final BoardQuery boardQuery;
    private final BoardLogCommand boardLogCommand;
    private final TradePropositionViewFactory tradePropositionViewFactory;

    public void save(TradeProposition tradeProposition) {
        tradePropositionRepository.save(tradeProposition);
        addLog(tradeProposition);
    }

    private void addLog(TradeProposition tradeProposition) {
        var board = boardQuery.get();
        boardLogCommand.add(
                new TradeStartedLogContent(
                        tradeProposition.getId(),
                        tradePropositionViewFactory.toView(tradeProposition))
                , board.getId());
    }
}

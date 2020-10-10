package pl.longhorn.autopoly.trade.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.trade.TradeProposition;
import pl.longhorn.autopoly.trade.TradePropositionRepository;

@Service
@RequiredArgsConstructor
public class TradePropositionQuery {

    private final TradePropositionRepository tradePropositionRepository;

    public TradeProposition get() {
        return tradePropositionRepository.get();
    }
}

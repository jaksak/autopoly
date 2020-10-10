package pl.longhorn.autopoly.trade;

import org.springframework.stereotype.Repository;

@Repository
public class TradePropositionRepository {

    private final Object monitor = new Object();
    private TradeProposition tradeProposition = null;

    public TradeProposition get() {
        synchronized (monitor) {
            return tradeProposition;
        }
    }

    public void save(TradeProposition tradeProposition) {
        synchronized (monitor) {
            this.tradeProposition = tradeProposition;
        }
    }

    public void clear() {
        synchronized (monitor) {
            tradeProposition = null;
        }
    }
}

package pl.longhorn.autopoly.state.history;

import org.springframework.stereotype.Repository;

@Repository
public class CheckStateHistoryRepository {

    private final Object monitor = new Object();
    private CheckStateHistory checkStateHistory;

    public CheckStateHistory get() {
        synchronized (monitor) {
            return checkStateHistory;
        }
    }

    public void save(CheckStateHistory checkStateHistory) {
        synchronized (monitor) {
            this.checkStateHistory = checkStateHistory;
        }
    }
}

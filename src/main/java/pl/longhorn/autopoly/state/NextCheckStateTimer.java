package pl.longhorn.autopoly.state;

import pl.longhorn.autopoly.state.history.CheckStateHistory;

import java.time.LocalDateTime;

public class NextCheckStateTimer {

    private static final long CHECK_STATE_SECONDS_INTERVAL = 10;

    public boolean shouldCheckState(CheckStateHistory checkStateHistory, LocalDateTime currentTime) {
        if (checkStateHistory == null) {
            return true;
        } else {
            return checkStateHistory.getLatestCheckStateAsLocalDateTime().plusSeconds(CHECK_STATE_SECONDS_INTERVAL).isBefore(currentTime);
        }
    }
}

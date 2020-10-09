package pl.longhorn.autopoly.state;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.state.history.CheckStateHistory;

import java.time.LocalDateTime;

@Component
public class NextCheckStateTimer {

    @Value("#{new Boolean('${autopoly.disableCheckStateBreak}')}")
    public boolean disableCheckStateBreak;

    private static final long CHECK_STATE_SECONDS_INTERVAL = 1;

    public boolean shouldCheckState(CheckStateHistory checkStateHistory, LocalDateTime currentTime) {
        if (checkStateHistory == null || disableCheckStateBreak) {
            return true;
        } else {
            return checkStateHistory.getLatestCheckStateAsLocalDateTime().plusSeconds(CHECK_STATE_SECONDS_INTERVAL).isBefore(currentTime);
        }
    }
}

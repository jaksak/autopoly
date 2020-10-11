package pl.longhorn.autopoly.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.action.auto.AutoActionCommand;
import pl.longhorn.autopoly.state.history.CheckStateHistory;
import pl.longhorn.autopoly.state.history.CheckStateHistoryRepository;
import pl.longhorn.autopoly.util.time.TimeProvider;

@Service
@RequiredArgsConstructor
public class CheckStateCommand {

    private static final int MAX_ACTION_AMOUNT = 200;
    private final NextCheckStateTimer nextCheckStateTimer;

    private final AutoActionCommand autoActionCommand;
    private final CheckStateHistoryRepository checkStateHistoryRepository;
    private final TimeProvider timeProvider;

    public synchronized void checkState() {
        if (nextCheckStateTimer.shouldCheckState(checkStateHistoryRepository.get(), timeProvider.getTime())) {
            checkStateInternal();
        }
        checkStateHistoryRepository.save(new CheckStateHistory(timeProvider.getTime()));
    }

    private void checkStateInternal() {
        boolean shouldContinue = true;
        for (int i = 0; i < MAX_ACTION_AMOUNT && shouldContinue; i++) {
            shouldContinue = autoActionCommand.doAutoAction();
        }
    }
}

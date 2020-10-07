package pl.longhorn.autopoly.state;

import org.junit.jupiter.api.Test;
import pl.longhorn.autopoly.state.history.CheckStateHistory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NextCheckStateTimerTest {


    @Test
    public void shouldReturnValidResult() {
        NextCheckStateTimer nextCheckStateTimer = new NextCheckStateTimer();
        var oldState = oldCheckState();

        var afterOneMs = nextCheckStateTimer.shouldCheckState(oldState, oldState.getLatestCheckStateAsLocalDateTime().plusNanos(1));
        var after1000Days = nextCheckStateTimer.shouldCheckState(oldState, oldState.getLatestCheckStateAsLocalDateTime().plusDays(1000));

        assertFalse(afterOneMs);
        assertTrue(after1000Days);
    }

    private CheckStateHistory oldCheckState() {
        return new CheckStateHistory(LocalDateTime.MIN);
    }
}
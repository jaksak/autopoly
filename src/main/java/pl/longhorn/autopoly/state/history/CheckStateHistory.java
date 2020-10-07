package pl.longhorn.autopoly.state.history;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CheckStateHistory {
    private final LocalDateTime latestCheckState;

    public LocalDateTime getLatestCheckStateAsLocalDateTime() {
        return latestCheckState;
    }
}

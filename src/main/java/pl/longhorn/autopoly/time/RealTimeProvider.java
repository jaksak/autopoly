package pl.longhorn.autopoly.time;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
class RealTimeProvider implements TimeProvider {
    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}

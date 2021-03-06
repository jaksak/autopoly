package pl.longhorn.autopoly.util.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TimeProvider {
    LocalDate getDate();

    LocalDateTime getTime();
}

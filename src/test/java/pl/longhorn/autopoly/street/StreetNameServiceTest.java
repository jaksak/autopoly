package pl.longhorn.autopoly.street;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StreetNameServiceTest {

    @Test
    public void shouldReturnValue() {
        var service = new StreetNameService();

        var result = service.getRandom();

        Assertions.assertFalse(result.isEmpty());
    }
}

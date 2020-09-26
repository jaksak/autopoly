package pl.longhorn.autopoly.street;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomStreetNameQueryTest {

    @Test
    public void shouldReturnValue() {
        var service = new RandomStreetNameQuery();

        var result = service.getRandom();

        Assertions.assertFalse(result.isEmpty());
    }
}

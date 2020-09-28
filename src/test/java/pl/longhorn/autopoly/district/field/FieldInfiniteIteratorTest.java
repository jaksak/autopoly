package pl.longhorn.autopoly.district.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FieldInfiniteIteratorTest {

    @Test
    public void shouldGoToNearbyField() {
        var iterator = AbcInfiniteIteratorFactory.prepareFieldInfiniteIterator();

        Assertions.assertEquals("A", iterator.next().getId());
        Assertions.assertEquals("B", iterator.next().getId());
        Assertions.assertEquals("C", iterator.next().getId());
        Assertions.assertEquals("A", iterator.next().getId());
    }
}
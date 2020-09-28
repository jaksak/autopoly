package pl.longhorn.autopoly.district.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistrictGoToCalculatorTest {

    @Test
    public void shouldGoToElementInTheSameIteration() {
        var infiniteIterator = AbcInfiniteIteratorFactory.prepareFieldInfiniteIterator();
        var calculator = new DistrictGoToCalculator(infiniteIterator);

        var after1Element = calculator.getFieldAfter("A", 1);
        var after2Element = calculator.getFieldAfter("A", 2);

        Assertions.assertEquals("B", after1Element.getId());
        Assertions.assertEquals("C", after2Element.getId());
    }

    @Test
    public void shouldGoToElementInNextIteration() {
        var infiniteIterator = AbcInfiniteIteratorFactory.prepareFieldInfiniteIterator();
        var calculator = new DistrictGoToCalculator(infiniteIterator);

        var after3Element = calculator.getFieldAfter("A", 3);
        var after4Element = calculator.getFieldAfter("A", 4);

        Assertions.assertEquals("A", after3Element.getId());
        Assertions.assertEquals("B", after4Element.getId());
    }

}
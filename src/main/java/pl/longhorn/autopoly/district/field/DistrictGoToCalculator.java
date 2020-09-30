package pl.longhorn.autopoly.district.field;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DistrictGoToCalculator {

    private final FieldInfiniteIterator fieldIterator;

    public AutopolyField getFieldAfter(String currentFieldId, int addend) {
        boolean exceedCurrent = false;
        AutopolyField testedField = null;
        for (int i = 0; i < addend; ) {
            testedField = fieldIterator.next();
            if (exceedCurrent) {
                i++;
            } else if (currentFieldId.equals(testedField.getId())) {
                exceedCurrent = true;
            }
        }
        return testedField;
    }

    public boolean isRecountFromZero() {
        return fieldIterator.isRecountFromZero();
    }
}

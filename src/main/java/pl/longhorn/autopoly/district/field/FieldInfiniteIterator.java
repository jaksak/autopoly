package pl.longhorn.autopoly.district.field;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class FieldInfiniteIterator implements Iterator<AutopolyField> {

    private final List<String> fieldIds;
    private final Map<String, AutopolyField> fieldById;
    private int i = -1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public AutopolyField next() {
        i++;
        if (i < fieldIds.size()) {
            return getFieldByPosition(i);
        } else {
            i = 0;
            return getFieldByPosition(0);
        }
    }

    private AutopolyField getFieldByPosition(int i) {
        var id = fieldIds.get(i);
        return fieldById.get(id);
    }
}

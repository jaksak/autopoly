package pl.longhorn.autopoly.district.field.definition.station;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StationField implements AutopolyField {

    private final String id;
    private final String name;
    private final boolean isLocked;

    @Override
    public AutopolyField reset() {
        return new StationField(id, name, false);
    }

    protected int getLockPrice() {
        return StationConfig.INITIAL_BUYING_PRICE / 2;
    }
}

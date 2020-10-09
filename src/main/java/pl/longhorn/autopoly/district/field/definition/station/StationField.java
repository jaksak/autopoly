package pl.longhorn.autopoly.district.field.definition.station;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StationField implements AutopolyField {

    private static final int INITIAL_BUYING_PRICE = 50;
    private static final int BONUS_FOR_OTHER_STATION = 50;

    private final String id;
    private final String name;
    private final boolean isLocked;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StationFieldView(id, name, isLocked);
    }

    protected int getRentPrice(int ownerOtherStationAmount) {
        return INITIAL_BUYING_PRICE + ((ownerOtherStationAmount - 1) * BONUS_FOR_OTHER_STATION);
    }

    @Override
    public AutopolyField reset() {
        return new StationField(id, name, false);
    }

    protected int getLockPrice() {
        return INITIAL_BUYING_PRICE / 2;
    }
}

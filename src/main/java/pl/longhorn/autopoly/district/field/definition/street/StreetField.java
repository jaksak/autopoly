package pl.longhorn.autopoly.district.field.definition.street;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter(AccessLevel.PACKAGE)
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StreetField implements AutopolyField {
    @Getter
    private final String id;
    @Getter
    private final String districtId;
    @Getter
    private final String name;
    private final int priceToBuy;
    private final int initialRentPrice;
    @Getter(AccessLevel.PACKAGE)
    private final int houseLvl;
    @Getter(AccessLevel.PACKAGE)
    private final boolean isLocked;

    @Override
    public AutopolyFieldDetailsView toView() {
        // TODO: to other policy...
        return new StreetFieldView(id, districtId, name, priceToBuy, 21, 37, houseLvl, isLocked);
    }

    @Override
    public AutopolyField reset() {
        return new StreetField(id, districtId, name, priceToBuy, initialRentPrice, 0, false);
    }
}

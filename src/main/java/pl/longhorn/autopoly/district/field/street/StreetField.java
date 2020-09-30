package pl.longhorn.autopoly.district.field.street;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class StreetField implements AutopolyField {
    private final String id;
    private final String districtId;
    private final String name;
    private final int price;
    private final int housePrice;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StreetFieldView(id, name, districtId, price, housePrice);
    }
}
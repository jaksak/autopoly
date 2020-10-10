package pl.longhorn.autopoly.district.field.definition.street;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.district.FieldTypeView;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@Builder
class StreetFieldView implements AutopolyFieldDetailsView {
    private final FieldTypeView type = FieldTypeView.PARCEL;
    private final String id;
    private final String districtId;
    private final String name;
    private final int price;
    private final int baseRentPrice;
    private final int currentRentPrice;
    private final int districtBonus;
    private final int housePrice;
    private final int hotelPrice;
    private final int houseLvl;
    private final boolean isLocked;
    private final int lockPrice;
}

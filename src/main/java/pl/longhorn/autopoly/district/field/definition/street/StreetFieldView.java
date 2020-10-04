package pl.longhorn.autopoly.district.field.definition.street;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.autopoly.district.FieldTypeView;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@AllArgsConstructor
class StreetFieldView implements AutopolyFieldDetailsView {
    private final FieldTypeView type = FieldTypeView.PARCEL;
    private final String id;
    private final String districtId;
    private final String name;
    private final int price;
    private final int housePrice;
    private final int hotelPrice;
    private final int houseLvl;
    private final boolean isLocked;
}
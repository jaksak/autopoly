package pl.longhorn.autopoly.district.field.definition.station;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.district.FieldTypeView;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@Builder
public class StationFieldView implements AutopolyFieldDetailsView {
    private final String id;
    private final String name;
    private final boolean isLocked;
    private final int lockPrice;
    private final FieldTypeView type = FieldTypeView.STATION;
    private final int baseRentPrice;
    private final int currentRentPrice;
    private final int districtBonus;
    private final int buyingPrice;
}

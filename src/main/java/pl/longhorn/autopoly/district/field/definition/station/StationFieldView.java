package pl.longhorn.autopoly.district.field.definition.station;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.district.FieldTypeView;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

import java.util.List;

@Getter
@Builder
public class StationFieldView implements AutopolyFieldDetailsView {
    private final String id;
    private final String name;
    private final boolean isLocked;
    private final int lockPrice;
    private final FieldTypeView type = FieldTypeView.STATION;
    private final int currentRentPrice;
    private final List<Integer> rentPriceByDistrictBonus;
    private final int buyingPrice;
}

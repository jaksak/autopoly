package pl.longhorn.autopoly.district.field.station;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StationField implements AutopolyField {

    private static final int STATION_PRICE_VALUE = 150;
    private final String id;
    private final String name;
    // TODO: real calulation
    private final int rentPrice = 3;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StationFieldView(id, name);
    }

    @Override
    public BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam) {
        // TODO: implement me!
        RentableParam param = RentableParam.builder()
                .fieldId(id)
                .ownerId(actionParam.getOwnerId())
                .player(actionParam.getPlayer())
                .fieldHasOwner(actionParam.fieldHasOwner())
                .isCalledByOwner(actionParam.isCalledByOwner())
                .buyingPrice(STATION_PRICE_VALUE)
                .rentPrice(rentPrice)
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }
}

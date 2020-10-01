package pl.longhorn.autopoly.district.field.street;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.districted.DistrictedField;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StreetField implements DistrictedField {
    private final String id;
    private final String districtId;
    private final String name;
    private final int price;
    private final int housePrice;
    // TODO: calculate it!
    private final int rentPrice = 10;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StreetFieldView(id, name, districtId, price, housePrice);
    }

    @Override
    public BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam) {
        RentableParam param = RentableParam.builder()
                .fieldId(id)
                .ownerId(actionParam.getOwnerId())
                .player(actionParam.getPlayer())
                .fieldHasOwner(actionParam.fieldHasOwner())
                .isCalledByOwner(actionParam.isCalledByOwner())
                .buyingPrice(price)
                .rentPrice(rentPrice)
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }
}

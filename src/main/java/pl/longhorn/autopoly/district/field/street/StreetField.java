package pl.longhorn.autopoly.district.field.street;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.ownership.FieldOwnershipChange;
import pl.longhorn.autopoly.district.ownership.MoneyChange;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StreetField implements AutopolyField {
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
        if (!actionParam.fieldHasOwner()) {
            return buyIfHaveMoney(actionParam);
        } else if (!actionParam.isCalledByOwner()) {
            return rentStreet(actionParam);
        } else {
            return BoardActionResult.builder()
                    .build();
        }
    }

    private BoardActionResult rentStreet(AutopolyFieldActionParam actionParam) {
        return BoardActionResult.builder()
                .moneyChange(new MoneyChange(actionParam.getOwnerId(), rentPrice))
                .moneyChange(new MoneyChange(actionParam.getPlayer().getId(), -rentPrice))
                .build();
    }

    private BoardActionResult buyIfHaveMoney(AutopolyFieldActionParam actionParam) {
        if (actionParam.getPlayer().getMoneyAmount() > price) {
            return BoardActionResult.builder()
                    .moneyChange(new MoneyChange(actionParam.getPlayer().getId(), -price))
                    .fieldOwnershipChange(new FieldOwnershipChange(actionParam.getPlayer().getId(), id))
                    .build();
        } else {
            return BoardActionResult.builder()
                    .build();
        }
    }
}

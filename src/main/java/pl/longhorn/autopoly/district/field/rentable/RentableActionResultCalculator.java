package pl.longhorn.autopoly.district.field.rentable;

import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.district.ownership.FieldOwnershipChange;
import pl.longhorn.autopoly.district.ownership.MoneyChange;
import pl.longhorn.autopoly.log.content.PlayerBuyFieldLogContent;
import pl.longhorn.autopoly.log.content.PlayerRentFieldLogContent;

public class RentableActionResultCalculator {

    public BoardActionResult calculate(RentableParam param) {
        if (!param.isFieldHasOwner()) {
            return buyIfHaveMoney(param);
        } else if (!param.isCalledByOwner()) {
            return rentStreet(param);
        } else {
            return BoardActionResult.builder()
                    .build();
        }
    }

    private BoardActionResult rentStreet(RentableParam param) {
        return BoardActionResult.builder()
                .moneyChange(new MoneyChange(param.getOwnerId(), param.getRentPrice()))
                .moneyChange(new MoneyChange(param.getPlayer().getId(), -param.getRentPrice()))
                .log(new PlayerRentFieldLogContent(param.getPlayer().getId(), param.getFieldId(), param.getRentPrice()))
                .build();
    }

    private BoardActionResult buyIfHaveMoney(RentableParam param) {
        if (param.getPlayer().getMoneyAmount() > param.getBuyingPrice()) {
            return BoardActionResult.builder()
                    .moneyChange(new MoneyChange(param.getPlayer().getId(), -param.getBuyingPrice()))
                    .fieldOwnershipChange(new FieldOwnershipChange(param.getPlayer().getId(), param.getFieldId()))
                    .log(new PlayerBuyFieldLogContent(param.getPlayer().getId(), param.getFieldId()))
                    .build();
        } else {
            return BoardActionResult.builder()
                    .build();
        }
    }
}

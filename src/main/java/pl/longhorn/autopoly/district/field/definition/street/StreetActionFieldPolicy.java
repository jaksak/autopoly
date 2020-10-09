package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;

@Component
@RequiredArgsConstructor
public class StreetActionFieldPolicy implements ActionFieldPolicy<StreetField> {

    private final StreetPriceService streetPriceService;

    @Override
    public BoardActionResult countActionAfterPlayerStay(AutopolyFieldActionParam<StreetField> actionParam) {
        var field = actionParam.getField();
        RentableParam param = RentableParam.builder()
                .fieldId(field.getId())
                .ownerId(actionParam.getOwnerId())
                .player(actionParam.getPlayer())
                .fieldHasOwner(actionParam.fieldHasOwner())
                .isCalledByOwner(actionParam.isCalledByOwner())
                .buyingPrice(field.getPriceToBuy())
                .rentPrice(streetPriceService.getRentPrice(field))
                .isLocked(field.isLocked())
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }
}

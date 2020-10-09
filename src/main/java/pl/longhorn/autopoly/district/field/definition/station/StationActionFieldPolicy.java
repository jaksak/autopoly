package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;

@Service
@RequiredArgsConstructor
public class StationActionFieldPolicy implements ActionFieldPolicy<StationField> {

    private final StationPriceService stationPriceService;

    @Override
    public BoardActionResult countActionAfterPlayerStay(AutopolyFieldActionParam<StationField> actionParam) {
        var field = actionParam.getField();
        RentableParam param = RentableParam.builder()
                .fieldId(field.getId())
                .ownerId(actionParam.getOwnerId())
                .player(actionParam.getPlayer())
                .fieldHasOwner(actionParam.fieldHasOwner())
                .isCalledByOwner(actionParam.isCalledByOwner())
                .buyingPrice(stationPriceService.getBuyingPrice())
                .rentPrice(stationPriceService.getRentPrice(field))
                .isLocked(field.isLocked())
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }
}

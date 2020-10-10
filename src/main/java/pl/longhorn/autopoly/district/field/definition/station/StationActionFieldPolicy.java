package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;

@Service
@RequiredArgsConstructor
public class StationActionFieldPolicy implements ActionFieldPolicy<StationField> {

    private final StationPriceService stationPriceService;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    @Override
    public BoardActionResult countActionAfterPlayerStay(StationField field, Player player) {
        RentableParam param = RentableParam.builder()
                .fieldId(field.getId())
                .ownerId(fieldOwnershipQuery.getOwner(field.getId()).orElse(null))
                .player(player)
                .buyingPrice(stationPriceService.getBuyingPrice())
                .rentPrice(stationPriceService.getRentPrice(field))
                .isLocked(field.isLocked())
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }
}

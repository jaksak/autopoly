package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;

@Component
@RequiredArgsConstructor
public class StreetActionFieldPolicy implements ActionFieldPolicy<StreetField> {

    private final StreetPriceService streetPriceService;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    @Override
    public BoardActionResult countActionAfterPlayerStay(StreetField field, Player player) {
        RentableParam param = RentableParam.builder()
                .fieldId(field.getId())
                .ownerId(fieldOwnershipQuery.getOwner(field.getId()).orElse(null))
                .player(player)
                .buyingPrice(streetPriceService.getBuyingPrice(field))
                .rentPrice(streetPriceService.getRentPrice(field))
                .isLocked(field.isLocked())
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }
}

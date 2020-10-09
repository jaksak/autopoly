package pl.longhorn.autopoly.district.field.definition.street;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StreetField implements AutopolyField {
    private final String id;
    private final String districtId;
    private final String name;
    private final int priceToBuy;
    private final int initialRentPrice;
    private final int houseLvl;
    private final boolean isLocked;

    // TODO: split to processor!
    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    @Override
    public AutopolyFieldDetailsView toView() {
        // TODO: to other policy...
        return new StreetFieldView(id, districtId, name, priceToBuy, 21, 37, houseLvl, isLocked);
    }

    @Override
    public BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam) {
        RentableParam param = RentableParam.builder()
                .fieldId(id)
                .ownerId(actionParam.getOwnerId())
                .player(actionParam.getPlayer())
                .fieldHasOwner(actionParam.fieldHasOwner())
                .isCalledByOwner(actionParam.isCalledByOwner())
                .buyingPrice(priceToBuy)
                .rentPrice(getRentPrice())
                .isLocked(isLocked)
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }

    @Override
    public AutopolyField reset() {
        return new StreetField(id, districtId, name, priceToBuy, initialRentPrice, 0, false, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
    }

    public int getRentPrice() {
        return initialRentPrice + (priceToBuy / 100 + 10 * houseLvl) + addBonusForFullDistrict();
    }

    private int addBonusForFullDistrict() {
        if (hasFullDistrict()) {
            return 50;
        } else {
            return 0;
        }
    }

    private boolean hasFullDistrict() {
        var playerId = fieldOwnershipQuery.getOwner(id);
        if (playerId.isPresent()) {
            var playerFieldIds = playerOwnershipQuery.get(playerId.get());
            var fieldIdsInDistrict = districtDetailsQuery.get().getFieldIdsByDistrictId(districtId);
            return playerFieldIds.containsAll(fieldIdsInDistrict);
        } else {
            return false;
        }
    }
}

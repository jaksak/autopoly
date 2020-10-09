package pl.longhorn.autopoly.district.field.definition.station;

import lombok.AccessLevel;
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

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StationField implements AutopolyField {

    private static final int INITIAL_PRICE = 150;
    private final String id;
    private final String name;
    // TODO: real calulation
    private final int rentPrice = 3;
    private final boolean isLocked;

    // TODO: split to processor!
    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StationFieldView(id, name, isLocked);
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
                .buyingPrice(getPrice())
                .rentPrice(rentPrice)
                .isLocked(isLocked)
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }

    private int getPrice() {
        return INITIAL_PRICE + countBonusForOtherStation();
    }

    private int countBonusForOtherStation() {
        List<String> otherStationId = getOtherStationId();
        var playerId = fieldOwnershipQuery.getOwner(id);
        if (playerId.isPresent()) {
            var playerFieldIds = playerOwnershipQuery.get(playerId.get());
            return (int) (otherStationId.stream()
                    .filter(playerFieldIds::contains)
                    .count() * 50);
        } else {
            return 0;
        }
    }

    private List<String> getOtherStationId() {
        return districtDetailsQuery.get().getStationFieldIds().stream()
                .filter(id -> !id.equals(this.id))
                .collect(Collectors.toList());
    }

    @Override
    public AutopolyField reset() {
        return new StationField(id, name, false, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
    }

    public int getLockPrice() {
        return INITIAL_PRICE / 2;
    }
}

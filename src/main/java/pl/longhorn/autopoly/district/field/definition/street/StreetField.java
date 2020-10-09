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
import pl.longhorn.autopoly.district.field.housable.HousableField;
import pl.longhorn.autopoly.district.field.housable.IllegalHouseLvlOperationException;
import pl.longhorn.autopoly.district.field.lockable.LockableField;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StreetField implements HousableField, LockableField {
    private static final int MAX_HOUSES_WITHOUT_HOTEL = 4;
    private static final int MAX_HOUSE_LVL = 5;
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
        return new StreetFieldView(id, districtId, name, priceToBuy, getHousePrice(), getHotelPrice(), houseLvl, isLocked);
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

    @Override
    public int getHousePrice() {
        return priceToBuy / 100 + 100;
    }

    @Override
    public int getHotelPrice() {
        return getHousePrice() + 50;
    }

    @Override
    public int getCurrentHousePrice() {
        return houseLvl <= MAX_HOUSES_WITHOUT_HOTEL ? getHotelPrice() : getHousePrice();
    }

    @Override
    public int getHouseLvl() {
        return houseLvl;
    }

    @Override
    public boolean shouldIncreaseHouseLvl() {
        return houseLvl < MAX_HOUSE_LVL && !isLocked;
    }

    @Override
    public boolean shouldDecreaseHouseLvl() {
        return houseLvl > 0;
    }

    @Override
    public HousableField increaseHouseLvl() throws IllegalHouseLvlOperationException {
        if (shouldIncreaseHouseLvl()) {
            return new StreetField(id, districtId, name, priceToBuy, initialRentPrice, houseLvl + 1, isLocked, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
        } else {
            throw new IllegalHouseLvlOperationException();
        }
    }

    @Override
    public HousableField decreaseHouseLvl() throws IllegalHouseLvlOperationException {
        if (shouldDecreaseHouseLvl()) {
            return new StreetField(id, districtId, name, priceToBuy, initialRentPrice, houseLvl - 1, isLocked, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
        } else {
            throw new IllegalHouseLvlOperationException();
        }
    }

    @Override
    public boolean shouldLock() {
        return houseLvl == 0 && !isLocked;
    }

    @Override
    public int getLockPrice() {
        return priceToBuy / 2;
    }

    @Override
    public LockableField lock() throws IllegalStateException {
        if (isLocked) {
            throw new IllegalStateException();
        } else {
            return new StreetField(id, districtId, name, priceToBuy, initialRentPrice, houseLvl, true, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
        }
    }

    @Override
    public LockableField unlock() throws IllegalStateException {
        if (isLocked) {
            return new StreetField(id, districtId, name, priceToBuy, initialRentPrice, houseLvl, false, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
        } else {
            throw new IllegalStateException();
        }
    }
}

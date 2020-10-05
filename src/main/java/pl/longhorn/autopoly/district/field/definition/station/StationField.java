package pl.longhorn.autopoly.district.field.definition.station;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.lockable.LockableField;
import pl.longhorn.autopoly.district.field.rentable.RentableActionResultCalculator;
import pl.longhorn.autopoly.district.field.rentable.RentableParam;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StationField implements LockableField {

    private static final int STATION_PRICE_VALUE = 150;
    private final String id;
    private final String name;
    // TODO: real calulation
    private final int rentPrice = 3;
    private final boolean isLocked;

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
                .buyingPrice(STATION_PRICE_VALUE)
                .rentPrice(rentPrice)
                .isLocked(isLocked)
                .build();
        return new RentableActionResultCalculator().calculate(param);
    }

    @Override
    public AutopolyField reset() {
        return new StationField(id, name, false);
    }

    @Override
    public boolean shouldLock() {
        return !isLocked();
    }

    @Override
    public int getLockPrice() {
        return STATION_PRICE_VALUE / 2;
    }

    @Override
    public LockableField lock() throws IllegalStateException {
        if (isLocked) {
            throw new IllegalStateException();
        } else {
            return new StationField(id, name, true);
        }
    }

    @Override
    public LockableField unlock() throws IllegalStateException {
        if (isLocked) {
            return new StationField(id, name, false);
        } else {
            throw new IllegalStateException();
        }
    }
}

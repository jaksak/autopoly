package pl.longhorn.autopoly.district.field.definition.street;

import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;

public class StreetLockFieldPolicy implements LockFieldPolicy<StreetField> {
    @Override
    public boolean shouldLock(StreetField field) {
        return field.getHouseLvl() == 0 && !field.isLocked();
    }

    @Override
    public boolean shouldUnlock(StreetField field) {
        return field.isLocked();
    }

    @Override
    public boolean isLocked(StreetField field) {
        return field.isLocked();
    }

    @Override
    public int getLockPrice(StreetField field) throws IllegalStateException {
        return field.getPriceToBuy() / 2;
    }

    @Override
    public StreetField lock(StreetField field) throws IllegalStateException {
        if (isLocked(field)) {
            throw new IllegalStateException();
        } else {
            return new StreetField(field.getId(), field.getDistrictId(), field.getName(), field.getPriceToBuy(), field.getInitialRentPrice(),
                    field.getHouseLvl(), true, field.getDistrictDetailsQuery(), field.getPlayerOwnershipQuery(),
                    field.getFieldOwnershipQuery());
        }
    }

    @Override
    public StreetField unlock(StreetField field) throws IllegalStateException {
        if (isLocked(field)) {
            return new StreetField(field.getId(), field.getDistrictId(), field.getName(), field.getPriceToBuy(), field.getInitialRentPrice(),
                    field.getHouseLvl(), false, field.getDistrictDetailsQuery(), field.getPlayerOwnershipQuery(),
                    field.getFieldOwnershipQuery());
        } else {
            throw new IllegalStateException();
        }
    }
}
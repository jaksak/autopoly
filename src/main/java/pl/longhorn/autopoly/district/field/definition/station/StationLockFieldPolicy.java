package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;

@Service
@RequiredArgsConstructor
public class StationLockFieldPolicy implements LockFieldPolicy<StationField> {
    @Override
    public boolean shouldLock(StationField field) {
        return !field.isLocked();
    }

    @Override
    public boolean shouldUnlock(StationField field) {
        return field.isLocked();
    }

    @Override
    // TODO: to remove?
    public boolean isLocked(StationField field) {
        return field.isLocked();
    }

    @Override
    public int getLockPrice(StationField field) throws IllegalStateException {
        return field.getLockPrice();
    }

    @Override
    public StationField lock(StationField field) throws IllegalStateException {
        if (field.isLocked()) {
            throw new IllegalStateException();
        } else {
            return new StationField(field.getId(), field.getName(), true);
        }
    }

    @Override
    public StationField unlock(StationField field) throws IllegalStateException {
        if (!field.isLocked()) {
            throw new IllegalStateException();
        } else {
            return new StationField(field.getId(), field.getName(), false);
        }
    }
}

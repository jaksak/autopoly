package pl.longhorn.autopoly.district.field.policy.lock;

import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Component
public class NoLockFieldPolicy<T extends AutopolyField> implements LockFieldPolicy<T> {

    @Override
    public boolean shouldLock(T field) {
        return false;
    }

    @Override
    public boolean shouldUnlock(T field) {
        return false;
    }

    @Override
    public boolean isLocked(T field) {
        return false;
    }

    @Override
    public int getLockPrice(T field) {
        throw new IllegalStateException();
    }

    @Override
    public T lock(T field) throws IllegalStateException {
        throw new IllegalStateException();
    }

    @Override
    public T unlock(T field) throws IllegalStateException {
        throw new IllegalStateException();
    }
}

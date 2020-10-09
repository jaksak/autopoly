package pl.longhorn.autopoly.district.field.policy.lock;

import pl.longhorn.autopoly.district.field.AutopolyField;

public interface LockFieldPolicy<T extends AutopolyField> {
    boolean shouldLock(T field);

    boolean shouldUnlock(T field);

    boolean isLocked(T field);

    int getLockPrice(T field) throws IllegalStateException;

    T lock(T field) throws IllegalStateException;

    T unlock(T field) throws IllegalStateException;
}

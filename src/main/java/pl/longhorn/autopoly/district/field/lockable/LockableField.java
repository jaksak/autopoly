package pl.longhorn.autopoly.district.field.lockable;

import pl.longhorn.autopoly.district.field.AutopolyField;

public interface LockableField extends AutopolyField {
    boolean shouldLock();

    boolean isLocked();

    int getLockPrice();

    LockableField lock() throws IllegalStateException;

    LockableField unlock() throws IllegalStateException;
}

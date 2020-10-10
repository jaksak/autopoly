package pl.longhorn.autopoly.district.field.policy;

import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.view.ViewFieldPolicy;

public interface FieldPolicy<T extends AutopolyField> {

    Class<T> getFieldClass();

    DistrictionFieldPolicy<T> getDistrictionFieldPolicy();

    HouseFieldPolicy<T> getHouseFieldPolicy();

    LockFieldPolicy<T> getLockFieldPolicy();

    ActionFieldPolicy<T> getActionFieldPolicy();

    ViewFieldPolicy<T> getViewFieldPolicy();
}

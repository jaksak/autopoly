package pl.longhorn.autopoly.district.field.policy;

import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;

public interface FieldPolicy<T extends AutopolyField> {

    Class<T> getFieldClass();

    DistrictionFieldPolicy<T> getDistrictionFieldPolicy();

    HouseFieldPolicy<T> getHouseFieldPolicy();
}

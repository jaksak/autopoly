package pl.longhorn.autopoly.district.field.policy;

import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;

public interface FieldPolicy<T extends AutopolyField> {

    Class<T> getFieldClass();

    DistrictionFieldPolicy<T> getDistrictionFieldPolicy();
}

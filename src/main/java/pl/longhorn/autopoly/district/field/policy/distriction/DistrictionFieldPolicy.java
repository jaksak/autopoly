package pl.longhorn.autopoly.district.field.policy.distriction;

import pl.longhorn.autopoly.district.field.AutopolyField;

public interface DistrictionFieldPolicy<T extends AutopolyField> {

    boolean hasAssignedDistrict();

    String getDistrictId(T field) throws IllegalArgumentException;
}

package pl.longhorn.autopoly.district.field.definition.street;

import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;

public class StreetDistrictionFieldPolicy implements DistrictionFieldPolicy<StreetField> {
    @Override
    public boolean hasAssignedDistrict() {
        return true;
    }

    @Override
    public String getDistrictId(StreetField field) throws IllegalArgumentException {
        return field.getDistrictId();
    }
}

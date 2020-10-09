package pl.longhorn.autopoly.district.field.policy.distriction;

import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Component
public class NoDistrictionFieldPolicy<T extends AutopolyField> implements DistrictionFieldPolicy<T> {
    @Override
    public boolean hasAssignedDistrict() {
        return false;
    }

    @Override
    public String getDistrictId(T field) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }
}

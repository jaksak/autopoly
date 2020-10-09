package pl.longhorn.autopoly.district.field.definition.empty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.NoDistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.NoHouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.lock.NoLockFieldPolicy;

@Component
@RequiredArgsConstructor
public class EmptyFieldPolicy implements FieldPolicy<EmptyField> {

    private final NoDistrictionFieldPolicy<EmptyField> noDistrictionFieldPolicy;
    private final NoHouseFieldPolicy<EmptyField> noHouseFieldPolicy;
    private final NoLockFieldPolicy<EmptyField> noLockFieldPolicy;

    @Override
    public Class<EmptyField> getFieldClass() {
        return EmptyField.class;
    }

    @Override
    public DistrictionFieldPolicy<EmptyField> getDistrictionFieldPolicy() {
        return noDistrictionFieldPolicy;
    }

    @Override
    public HouseFieldPolicy<EmptyField> getHouseFieldPolicy() {
        return noHouseFieldPolicy;
    }

    @Override
    public LockFieldPolicy<EmptyField> getLockFieldPolicy() {
        return noLockFieldPolicy;
    }
}

package pl.longhorn.autopoly.district.field.definition.start;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.NoDistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.NoHouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.lock.NoLockFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.view.ViewFieldPolicy;

@Component
@RequiredArgsConstructor
public class StartFieldPolicy implements FieldPolicy<StartField> {

    private final NoDistrictionFieldPolicy<StartField> noDistrictionFieldPolicy;
    private final NoHouseFieldPolicy<StartField> noHouseFieldPolicy;
    private final NoLockFieldPolicy<StartField> noLockFieldPolicy;
    private final StartActionFieldPolicy startActionFieldPolicy = new StartActionFieldPolicy();

    @Override
    public Class<StartField> getFieldClass() {
        return StartField.class;
    }

    @Override
    public DistrictionFieldPolicy<StartField> getDistrictionFieldPolicy() {
        return noDistrictionFieldPolicy;
    }

    @Override
    public HouseFieldPolicy<StartField> getHouseFieldPolicy() {
        return noHouseFieldPolicy;
    }

    @Override
    public LockFieldPolicy<StartField> getLockFieldPolicy() {
        return noLockFieldPolicy;
    }

    @Override
    public ActionFieldPolicy<StartField> getActionFieldPolicy() {
        return startActionFieldPolicy;
    }

    @Override
    public ViewFieldPolicy<StartField> getViewFieldPolicy() {
        return field -> new StartFieldView(field.getId());
    }
}

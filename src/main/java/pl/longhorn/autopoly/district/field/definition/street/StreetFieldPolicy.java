package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.view.ViewFieldPolicy;

@Component
@RequiredArgsConstructor
public class StreetFieldPolicy implements FieldPolicy<StreetField> {

    private final StreetDistrictionFieldPolicy districtionFieldPolicy = new StreetDistrictionFieldPolicy();
    private final StreetHouseFieldPolicy houseFieldPolicy;
    private final StreetLockFieldPolicy streetLockFieldPolicy;
    private final StreetActionFieldPolicy streetActionFieldPolicy;
    private final StreetViewFieldPolicy streetViewFieldPolicy;

    @Override
    public Class<StreetField> getFieldClass() {
        return StreetField.class;
    }

    @Override
    public DistrictionFieldPolicy<StreetField> getDistrictionFieldPolicy() {
        return districtionFieldPolicy;
    }

    @Override
    public HouseFieldPolicy<StreetField> getHouseFieldPolicy() {
        return houseFieldPolicy;
    }

    @Override
    public LockFieldPolicy<StreetField> getLockFieldPolicy() {
        return streetLockFieldPolicy;
    }

    @Override
    public ActionFieldPolicy<StreetField> getActionFieldPolicy() {
        return streetActionFieldPolicy;
    }

    @Override
    public ViewFieldPolicy<StreetField> getViewFieldPolicy() {
        return streetViewFieldPolicy;
    }
}

package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.NoDistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.NoHouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;

@Component
@RequiredArgsConstructor
public class StationFieldPolicy implements FieldPolicy<StationField> {

    private final NoDistrictionFieldPolicy<StationField> noDistrictionFieldPolicy;
    private final NoHouseFieldPolicy<StationField> noHouseFieldPolicy;
    private final StationLockFieldPolicy stationLockFieldPolicy = new StationLockFieldPolicy();
    private final StationActionFieldPolicy stationActionFieldPolicy;

    @Override
    public Class<StationField> getFieldClass() {
        return StationField.class;
    }

    @Override
    public DistrictionFieldPolicy<StationField> getDistrictionFieldPolicy() {
        return noDistrictionFieldPolicy;
    }

    @Override
    public HouseFieldPolicy<StationField> getHouseFieldPolicy() {
        return noHouseFieldPolicy;
    }

    @Override
    public LockFieldPolicy<StationField> getLockFieldPolicy() {
        return stationLockFieldPolicy;
    }

    @Override
    public ActionFieldPolicy<StationField> getActionFieldPolicy() {
        return stationActionFieldPolicy;
    }
}

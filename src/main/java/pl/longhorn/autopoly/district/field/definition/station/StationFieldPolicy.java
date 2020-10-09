package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.NoDistrictionFieldPolicy;

@Component
@RequiredArgsConstructor
public class StationFieldPolicy implements FieldPolicy<StationField> {

    private final NoDistrictionFieldPolicy<StationField> noDistrictionFieldPolicy;

    @Override
    public Class<StationField> getFieldClass() {
        return StationField.class;
    }

    @Override
    public DistrictionFieldPolicy<StationField> getDistrictionFieldPolicy() {
        return noDistrictionFieldPolicy;
    }
}

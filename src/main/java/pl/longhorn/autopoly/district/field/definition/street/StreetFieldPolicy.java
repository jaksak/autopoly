package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;

@Component
@RequiredArgsConstructor
public class StreetFieldPolicy implements FieldPolicy<StreetField> {

    private final StreetDistrictionFieldPolicy streetDistrictionFieldPolicy = new StreetDistrictionFieldPolicy();

    @Override
    public Class<StreetField> getFieldClass() {
        return StreetField.class;
    }

    @Override
    public DistrictionFieldPolicy<StreetField> getDistrictionFieldPolicy() {
        return streetDistrictionFieldPolicy;
    }
}

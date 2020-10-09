package pl.longhorn.autopoly.district.field.definition.empty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.NoDistrictionFieldPolicy;

@Component
@RequiredArgsConstructor
public class EmptyFieldPolicy implements FieldPolicy<EmptyField> {

    private final NoDistrictionFieldPolicy<EmptyField> noDistrictionFieldPolicy;

    @Override
    public Class<EmptyField> getFieldClass() {
        return EmptyField.class;
    }

    @Override
    public DistrictionFieldPolicy<EmptyField> getDistrictionFieldPolicy() {
        return noDistrictionFieldPolicy;
    }
}

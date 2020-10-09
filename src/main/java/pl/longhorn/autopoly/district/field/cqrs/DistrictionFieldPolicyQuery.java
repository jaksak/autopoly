package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.policy.FieldPolicyFactory;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;

@Component
@RequiredArgsConstructor
public class DistrictionFieldPolicyQuery {

    private final FieldPolicyFactory fieldPolicyFactory;

    public <T extends AutopolyField> DistrictionFieldPolicy<T> get(T autopolyField) {
        return fieldPolicyFactory.getPolicy(autopolyField).getDistrictionFieldPolicy();
    }
}

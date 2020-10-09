package pl.longhorn.autopoly.district.field.policy;

import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.AutopolyField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FieldPolicyFactory {

    private final Map<Class<? extends AutopolyField>, FieldPolicy<? extends AutopolyField>> policyByClass = new HashMap<>();

    public FieldPolicyFactory(List<FieldPolicy<? extends AutopolyField>> fieldsPolicy) {
        fieldsPolicy.forEach(fieldPolicy -> policyByClass.put(fieldPolicy.getFieldClass(), fieldPolicy));
    }

    public <T extends AutopolyField> FieldPolicy<T> getPolicy(T field) {
        var policy = (FieldPolicy<T>) policyByClass.get(field.getClass());
        if (policy == null) {
            throw new FieldPolicyNotImplementedException(field.getClass());
        } else {
            return policy;
        }
    }
}

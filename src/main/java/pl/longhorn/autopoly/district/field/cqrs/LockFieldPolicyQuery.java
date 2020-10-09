package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.policy.FieldPolicyFactory;
import pl.longhorn.autopoly.district.field.policy.lock.LockFieldPolicy;

@Service
@RequiredArgsConstructor
public class LockFieldPolicyQuery {

    private final FieldPolicyFactory fieldPolicyFactory;

    public <T extends AutopolyField> LockFieldPolicy<T> get(T field) {
        return fieldPolicyFactory.getPolicy(field).getLockFieldPolicy();
    }
}

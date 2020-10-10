package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.policy.FieldPolicyFactory;

@Service
@RequiredArgsConstructor
public class FieldViewQuery {

    private final FieldPolicyFactory fieldPolicyFactory;

    public AutopolyFieldDetailsView get(AutopolyField field) {
        return fieldPolicyFactory.getPolicy(field).getViewFieldPolicy().getView(field);
    }
}

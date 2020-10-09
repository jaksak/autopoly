package pl.longhorn.autopoly.district.field.policy;

import pl.longhorn.autopoly.district.field.AutopolyField;

public class FieldPolicyNotImplementedException extends RuntimeException {
    public FieldPolicyNotImplementedException(Class<? extends AutopolyField> fieldClass) {
        super(fieldClass.getName());
    }
}

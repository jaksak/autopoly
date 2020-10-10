package pl.longhorn.autopoly.district.field.policy.view;

import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

public interface ViewFieldPolicy<T extends AutopolyField> {
    AutopolyFieldDetailsView getView(T field);
}

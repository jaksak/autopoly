package pl.longhorn.autopoly.field.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.autopoly.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.field.FieldTypeView;

@Getter
@AllArgsConstructor
class StartFieldView implements AutopolyFieldDetailsView {

    private final FieldTypeView type = FieldTypeView.START;
    private final String id;
}

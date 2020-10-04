package pl.longhorn.autopoly.district.field.definition.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.autopoly.district.FieldTypeView;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@AllArgsConstructor
class StartFieldView implements AutopolyFieldDetailsView {

    private final FieldTypeView type = FieldTypeView.START;
    private final String id;
}

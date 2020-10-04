package pl.longhorn.autopoly.district.field.definition.empty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.FieldTypeView;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor
class EmptyFieldView implements AutopolyFieldDetailsView {
    private final String id;
    private final FieldTypeView type = FieldTypeView.EMPTY;
}

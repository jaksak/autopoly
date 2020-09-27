package pl.longhorn.autopoly.field.empty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.field.FieldTypeView;

@Getter
@RequiredArgsConstructor
class EmptyFieldView implements AutopolyFieldDetailsView {
    private final String id;
    private final FieldTypeView type = FieldTypeView.EMPTY;
}

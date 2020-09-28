package pl.longhorn.autopoly.district.field.empty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor
class EmptyField implements AutopolyField {

    private final String id;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new EmptyFieldView(id);
    }
}

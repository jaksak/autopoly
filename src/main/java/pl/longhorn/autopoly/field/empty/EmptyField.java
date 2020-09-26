package pl.longhorn.autopoly.field.empty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.field.AutopolyField;
import pl.longhorn.autopoly.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor
public class EmptyField implements AutopolyField {

    private final String id;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new EmptyFieldView(id);
    }
}

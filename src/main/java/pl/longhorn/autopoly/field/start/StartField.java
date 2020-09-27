package pl.longhorn.autopoly.field.start;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.field.AutopolyField;
import pl.longhorn.autopoly.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class StartField implements AutopolyField {

    private final String id;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StartFieldView(id);
    }
}

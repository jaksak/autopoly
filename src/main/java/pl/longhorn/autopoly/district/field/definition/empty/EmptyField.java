package pl.longhorn.autopoly.district.field.definition.empty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Getter
@RequiredArgsConstructor
class EmptyField implements AutopolyField {

    private final String id;

    @Override
    public AutopolyField reset() {
        return this;
    }
}

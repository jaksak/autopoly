package pl.longhorn.autopoly.district.field.definition.start;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class StartField implements AutopolyField {

    private final String id;

    @Override
    public AutopolyField reset() {
        return this;
    }
}

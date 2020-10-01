package pl.longhorn.autopoly.district.field.start;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class StartField implements AutopolyField {

    private final String id;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StartFieldView(id);
    }

    @Override
    public BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam) {
        return BoardActionResult.builder()
                .build();
    }
}

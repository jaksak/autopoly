package pl.longhorn.autopoly.district.field.station;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StationField implements AutopolyField {

    private final String id;
    private final String name;

    @Override
    public AutopolyFieldDetailsView toView() {
        return new StationFieldView(id, name);
    }

    @Override
    public BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam) {
        // TODO: implement me!
        return BoardActionResult.builder()
                .build();
    }
}

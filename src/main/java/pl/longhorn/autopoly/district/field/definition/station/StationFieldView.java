package pl.longhorn.autopoly.district.field.definition.station;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.FieldTypeView;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor
public class StationFieldView implements AutopolyFieldDetailsView {
    private final String id;
    private final String name;
    private final boolean isLocked;
    private final FieldTypeView type = FieldTypeView.STATION;
}
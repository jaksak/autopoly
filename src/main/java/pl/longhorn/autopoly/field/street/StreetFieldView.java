package pl.longhorn.autopoly.field.street;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.autopoly.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.field.FieldTypeView;

@Getter
@AllArgsConstructor
public class StreetFieldView implements AutopolyFieldDetailsView {
    private final FieldTypeView type = FieldTypeView.PARCEL;
    private final String id;
    private final String name;
    private final int price;
}

package pl.longhorn.autopoly.board.model.view.field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParcelFieldView implements AutopolyFieldDetails {
    private final FieldTypeView type = FieldTypeView.PARCEL;
    private final String id;
    private final String name;
    private final int price;
}

package pl.longhorn.autopoly.district.field;

import pl.longhorn.autopoly.district.FieldTypeView;

import java.io.Serializable;

public interface AutopolyFieldDetailsView extends Serializable {
    String getId();

    FieldTypeView getType();
}

package pl.longhorn.autopoly.field;

import java.io.Serializable;

public interface AutopolyFieldDetailsView extends Serializable {
    String getId();

    FieldTypeView getType();
}

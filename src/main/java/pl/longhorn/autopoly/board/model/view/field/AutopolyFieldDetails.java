package pl.longhorn.autopoly.board.model.view.field;

import java.io.Serializable;

public interface AutopolyFieldDetails extends Serializable {
    FieldTypeView getType();

    String getId();
}

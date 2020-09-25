package pl.longhorn.autopoly.board.model.view.field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StartFieldView implements AutopolyFieldDetails {

    private final FieldTypeView type = FieldTypeView.START;
    private final String id;
}

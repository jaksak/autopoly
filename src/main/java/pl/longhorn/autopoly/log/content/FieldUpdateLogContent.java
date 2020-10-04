package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;

@Getter
@RequiredArgsConstructor
public class FieldUpdateLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.FIELD_UPDATE;
    private final AutopolyFieldDetailsView field;
}

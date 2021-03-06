package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.district.FieldService;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.FieldUpdateLogContent;

@Service
@RequiredArgsConstructor
public class ResetFieldCommand {

    private final FieldService fieldService;
    private final FieldQuery fieldQuery;
    private final BoardQuery boardQuery;
    private final BoardLogCommand boardLogCommand;
    private final FieldViewQuery fieldViewQuery;

    public void reset(String fieldId) {
        var field = fieldQuery.get(fieldId);
        var fieldAfterReset = field.reset();
        fieldService.update(fieldAfterReset);
        boardLogCommand.add(new FieldUpdateLogContent(fieldViewQuery.get(fieldAfterReset)), boardQuery.get().getId());
    }
}

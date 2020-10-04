package pl.longhorn.autopoly.district.field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.BoardQuery;
import pl.longhorn.autopoly.district.FieldService;
import pl.longhorn.autopoly.district.field.lockable.LockableField;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.FieldUpdateLogContent;
import pl.longhorn.autopoly.player.UpdateMoneyCommand;

@Service
@RequiredArgsConstructor
public class LockFieldCommand {

    private final FieldService fieldService;
    private final FieldQuery fieldQuery;
    private final UpdateMoneyCommand updateMoneyCommand;
    private final BoardQuery boardQuery;
    private final BoardLogCommand boardLogCommand;

    public void lock(String fieldId, String ownerId) {
        var field = fieldQuery.getField(fieldId);
        if (field instanceof LockableField) {
            var lockableField = (LockableField) field;
            if (lockableField.shouldLock()) {
                var lockedField = lockableField.lock();
                fieldService.update(lockedField);
                updateMoneyCommand.updateMoney(ownerId, lockedField.getLockPrice());
                boardLogCommand.add(new FieldUpdateLogContent(lockedField.toView()), boardQuery.get().getId());
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
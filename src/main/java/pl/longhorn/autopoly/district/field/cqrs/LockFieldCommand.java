package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
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
        var field = fieldQuery.get(fieldId);
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

    public void unlock(String fieldId, String ownerId) {
        var field = fieldQuery.get(fieldId);
        if (field instanceof LockableField) {
            var lockableField = (LockableField) field;
            if (lockableField.isLocked()) {
                var unlockedField = lockableField.unlock();
                fieldService.update(unlockedField);
                updateMoneyCommand.updateMoney(ownerId, -unlockedField.getLockPrice());
                boardLogCommand.add(new FieldUpdateLogContent(unlockedField.toView()), boardQuery.get().getId());
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}

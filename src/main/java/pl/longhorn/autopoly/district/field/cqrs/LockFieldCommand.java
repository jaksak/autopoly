package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.district.FieldService;
import pl.longhorn.autopoly.district.field.policy.FieldPolicyFactory;
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
    private final FieldPolicyFactory fieldPolicyFactory;
    private final FieldViewQuery fieldViewQuery;

    public void lock(String fieldId, String ownerId) {
        var field = fieldQuery.get(fieldId);
        var lockPolicy = fieldPolicyFactory.getPolicy(field).getLockFieldPolicy();
        if (lockPolicy.shouldLock(field)) {
            var lockedField = lockPolicy.lock(field);
            fieldService.update(lockedField);
            updateMoneyCommand.updateMoney(ownerId, lockPolicy.getLockPrice(field));
            boardLogCommand.add(new FieldUpdateLogContent(fieldViewQuery.get(lockedField)), boardQuery.get().getId());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void unlock(String fieldId, String ownerId) {
        var field = fieldQuery.get(fieldId);
        var lockPolicy = fieldPolicyFactory.getPolicy(field).getLockFieldPolicy();
        if (lockPolicy.shouldUnlock(field)) {
            var unlockedField = lockPolicy.unlock(field);
            fieldService.update(unlockedField);
            updateMoneyCommand.updateMoney(ownerId, -lockPolicy.getLockPrice(field));
            boardLogCommand.add(new FieldUpdateLogContent(fieldViewQuery.get(unlockedField)), boardQuery.get().getId());
        } else {
            throw new IllegalArgumentException();
        }
    }
}

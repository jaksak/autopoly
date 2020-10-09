package pl.longhorn.autopoly.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.cqrs.LockFieldCommand;
import pl.longhorn.autopoly.district.field.cqrs.LockFieldPolicyQuery;
import pl.longhorn.autopoly.player.PlayerOwnershipAccessor;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LockAnyFieldProcessor {

    private final LockFieldCommand lockFieldCommand;
    private final PlayerOwnershipAccessor playerOwnershipAccessor;
    private final LockFieldPolicyQuery lockFieldPolicyQuery;
    private final Randomizer randomizer;

    public boolean tryLockProperty(String playerId) {
        var propertyToLock = getPropertyToLock(playerId);
        if (propertyToLock.isPresent()) {
            lockFieldCommand.lock(propertyToLock.get().getId(), playerId);
            return true;
        }
        return false;
    }

    private Optional<AutopolyField> getPropertyToLock(String playerId) {
        List<AutopolyField> fieldsToLock = new LinkedList<>();
        for (var field : playerOwnershipAccessor.get(playerId)) {
            var lockPolicy = lockFieldPolicyQuery.get(field);
            if (lockPolicy.shouldLock(field)) {
                fieldsToLock.add(field);
            }
        }
        return randomizer.getRandom(fieldsToLock);
    }
}

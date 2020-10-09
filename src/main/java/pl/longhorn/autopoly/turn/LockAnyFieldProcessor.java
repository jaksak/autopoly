package pl.longhorn.autopoly.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.district.field.cqrs.LockFieldCommand;
import pl.longhorn.autopoly.district.field.lockable.LockableField;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LockAnyFieldProcessor {

    private final LockFieldCommand lockFieldCommand;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldQuery fieldQuery;

    public boolean tryLockProperty(String playerId) {
        var propertyToLock = getPropertyToLock(playerId);
        if (propertyToLock.isPresent()) {
            lockFieldCommand.lock(propertyToLock.get().getId(), playerId);
            return true;
        }
        return false;
    }

    private Optional<LockableField> getPropertyToLock(String playerId) {
        return playerOwnershipQuery.get(playerId).stream()
                .map(fieldQuery::get)
                .filter(field -> field instanceof LockableField)
                .map(field -> (LockableField) field)
                .filter(LockableField::shouldLock)
                .findAny();
    }
}

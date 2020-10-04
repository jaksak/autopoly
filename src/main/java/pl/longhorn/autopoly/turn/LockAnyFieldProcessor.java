package pl.longhorn.autopoly.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.FieldQuery;
import pl.longhorn.autopoly.district.field.LockFieldCommand;
import pl.longhorn.autopoly.district.field.lockable.LockableField;
import pl.longhorn.autopoly.player.Player;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LockAnyFieldProcessor {

    private final LockFieldCommand lockFieldCommand;
    private final FieldQuery fieldQuery;

    public boolean tryLockProperty(Player player) {
        var propertyToLock = getPropertyToLock(player);
        if (propertyToLock.isPresent()) {
            lockFieldCommand.lock(propertyToLock.get().getId(), player.getId());
            return true;
        }
        return false;
    }

    private Optional<LockableField> getPropertyToLock(Player player) {
        return player.getOwnedFieldIds().stream()
                .map(fieldQuery::getField)
                .filter(field -> field instanceof LockableField)
                .map(field -> (LockableField) field)
                .filter(LockableField::shouldLock)
                .findAny();
    }
}

package pl.longhorn.autopoly.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.cqrs.DecreaseHouseLvlCommand;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.district.field.housable.HousableField;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DecreaseHouseLvlAnyFieldProcessor {

    private final FieldQuery fieldQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final DecreaseHouseLvlCommand decreaseHouseLvlCommand;

    public boolean tryDecreaseHouseLvl(String playerId) {
        var propertyToDecreaseLvl = getPropertyToDecreaseLvl(playerId);
        if (propertyToDecreaseLvl.isPresent()) {
            decreaseHouseLvlCommand.decreaseHouseLvl(propertyToDecreaseLvl.get().getId(), playerId);
            return true;
        }
        return false;
    }

    private Optional<HousableField> getPropertyToDecreaseLvl(String playerId) {
        return playerOwnershipQuery.get(playerId).stream()
                .map(fieldQuery::getField)
                .filter(field -> field instanceof HousableField)
                .map(field -> (HousableField) field)
                .filter(HousableField::shouldDecreaseHouseLvl)
                .findAny();
    }
}

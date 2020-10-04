package pl.longhorn.autopoly.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.DecreaseHouseLvlCommand;
import pl.longhorn.autopoly.district.field.FieldQuery;
import pl.longhorn.autopoly.district.field.housable.HousableField;
import pl.longhorn.autopoly.player.Player;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DecreaseHouseLvlAnyFieldProcessor {

    private final FieldQuery fieldQuery;
    private final DecreaseHouseLvlCommand decreaseHouseLvlCommand;

    public boolean tryDecreaseHouseLvl(Player player) {
        var propertyToDecreaseLvl = getPropertyToDecreaseLvl(player);
        if (propertyToDecreaseLvl.isPresent()) {
            decreaseHouseLvlCommand.decreaseHouseLvl(propertyToDecreaseLvl.get().getId(), player.getId());
            return true;
        }
        return false;
    }

    private Optional<HousableField> getPropertyToDecreaseLvl(Player player) {
        return player.getOwnedFieldIds().stream()
                .map(fieldQuery::getField)
                .filter(field -> field instanceof HousableField)
                .map(field -> (HousableField) field)
                .filter(HousableField::shouldDecreaseHouseLvl)
                .findAny();
    }
}

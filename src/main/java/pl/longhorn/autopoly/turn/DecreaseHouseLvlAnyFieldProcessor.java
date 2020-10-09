package pl.longhorn.autopoly.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.district.field.cqrs.HouseFieldPolicyQuery;
import pl.longhorn.autopoly.district.field.cqrs.HouseLvlCommand;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DecreaseHouseLvlAnyFieldProcessor {

    private final FieldQuery fieldQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final HouseLvlCommand houseLvlCommand;
    private final HouseFieldPolicyQuery houseFieldPolicyQuery;
    private final Randomizer randomizer;

    public boolean tryDecreaseHouseLvl(String playerId) {
        var propertyToDecreaseLvl = getPropertyToDecreaseLvl(playerId);
        if (propertyToDecreaseLvl.isPresent()) {
            houseLvlCommand.decreaseHouseLvl(propertyToDecreaseLvl.get().getId(), playerId);
            return true;
        }
        return false;
    }

    private Optional<AutopolyField> getPropertyToDecreaseLvl(String playerId) {
        List<AutopolyField> propertiesToDecreaseLvl = new LinkedList<>();
        for (String fieldId : playerOwnershipQuery.get(playerId)) {
            var field = fieldQuery.get(fieldId);
            var housePolicy = houseFieldPolicyQuery.get(field);
            if (housePolicy.shouldDecreaseHouseLvl(field)) {
                propertiesToDecreaseLvl.add(field);
            }
        }
        return randomizer.getRandom(propertiesToDecreaseLvl);
    }
}

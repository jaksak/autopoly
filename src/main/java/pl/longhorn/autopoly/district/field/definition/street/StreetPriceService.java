package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.policy.house.IllegalHouseLvlOperationException;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

@Service
@RequiredArgsConstructor
public class StreetPriceService {

    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    public int getBuyingPrice(StreetField streetField) {
        return streetField.getPriceToBuy();
    }

    public int getRentPrice(StreetField field) {
        return getCalculator(field).calculate(field.getHouseLvl(), hasFullDistrict(field));
    }

    public int getHousePrice(StreetField field) throws IllegalHouseLvlOperationException {
        return field.getPriceToBuy() / 100 + 100;
    }

    public int getHotelPrice(StreetField field) throws IllegalHouseLvlOperationException {
        return getHousePrice(field) + 50;
    }

    public StreetRentPriceCalculator getCalculator(StreetField field) {
        return new StreetRentPriceCalculator(field.getInitialRentPrice(), getHousePrice(field), getHotelPrice(field), getFullDistrictBonus());
    }

    public int getFullDistrictBonus() {
        return 50;
    }

    private boolean hasFullDistrict(StreetField streetField) {
        var playerId = fieldOwnershipQuery.getOwner(streetField.getId());
        if (playerId.isPresent()) {
            var playerFieldIds = playerOwnershipQuery.get(playerId.get());
            var fieldIdsInDistrict = districtDetailsQuery.get().getFieldIdsByDistrictId(streetField.getDistrictId());
            return playerFieldIds.containsAll(fieldIdsInDistrict);
        } else {
            return false;
        }
    }
}

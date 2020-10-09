package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
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

    public int getRentPrice(StreetField streetField) {
        return streetField.getInitialRentPrice() + (streetField.getPriceToBuy() / 5 + 10 * streetField.getHouseLvl()) + addBonusForFullDistrict(streetField);
    }

    private int addBonusForFullDistrict(StreetField streetField) {
        if (hasFullDistrict(streetField)) {
            return 50;
        } else {
            return 0;
        }
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

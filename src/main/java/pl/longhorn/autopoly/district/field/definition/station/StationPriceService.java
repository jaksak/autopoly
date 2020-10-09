package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

@Service
@RequiredArgsConstructor
public class StationPriceService {

    private final DistrictDetailsQuery districtDetailsQuery;
    private final FieldOwnershipQuery fieldOwnershipQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;

    public int getBuyingPrice() {
        return 150;
    }

    public int getRentPrice(StationField field) {
        int playerStationAmount = fieldOwnershipQuery.getOwner(field.getId()).map(this::getPlayerStationAmount).orElse(1);
        return field.getRentPrice(playerStationAmount);
    }

    private int getPlayerStationAmount(String playerId) {
        var playerFieldIds = playerOwnershipQuery.get(playerId);
        return (int) districtDetailsQuery.get().getStationFieldIds().stream()
                .filter(playerFieldIds::contains)
                .count();
    }
}

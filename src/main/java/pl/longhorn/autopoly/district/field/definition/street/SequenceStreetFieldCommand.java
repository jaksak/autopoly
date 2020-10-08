package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

@Service
@RequiredArgsConstructor
public class SequenceStreetFieldCommand {

    private static final int INITIAL_PRICE = 50;
    private static final int PRICE_FLUX_FOR_THE_SAME_DISTRICT = 40;
    private static final int PRICE_FLUX = 10;
    private final Randomizer randomizer;
    private int latestPrice = INITIAL_PRICE;
    private String latestDistrict;

    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    public StreetField generate(String id, String name, String districtId) {
        int currentPrice = getCurrentPrice(districtId);
        latestPrice = currentPrice;
        latestDistrict = districtId;
        return new StreetField(id, name, districtId, currentPrice, currentPrice / 50 * 10 + 25, 0, false, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
    }

    private int getCurrentPrice(String currentDistrictId) {
        return latestPrice + randomizer.nextInt(currentDistrictId.equals(latestDistrict) ? PRICE_FLUX_FOR_THE_SAME_DISTRICT : PRICE_FLUX);
    }
}

package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public StreetField generate(String id, String name, String districtId) {
        int currentPrice = getCurrentPrice(districtId);
        latestPrice = currentPrice;
        latestDistrict = districtId;
        return new StreetField(id, districtId, name, currentPrice, currentPrice / 50 * 10 + 25, 0, false);
    }

    private int getCurrentPrice(String currentDistrictId) {
        return latestPrice + randomizer.nextInt(currentDistrictId.equals(latestDistrict) ? PRICE_FLUX_FOR_THE_SAME_DISTRICT : PRICE_FLUX);
    }
}

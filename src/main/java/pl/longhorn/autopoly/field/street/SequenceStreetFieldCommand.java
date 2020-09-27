package pl.longhorn.autopoly.field.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.randomizer.Randomizer;

@Service
@RequiredArgsConstructor
public class SequenceStreetFieldCommand {

    private static final int INITIAL_PRICE = 50;
    private static final int PRICE_FLUX = 25;
    private static final int HOUSE_PRICE_FLUX = 10;
    private final Randomizer randomizer;
    private String latestDistrictId = "";
    private int latestPrice = INITIAL_PRICE;
    private int latestHousePrice = 25;

    public StreetField generate(String id, String name, String districtId) {
        int currentPrice = latestPrice + randomizer.nextInt(PRICE_FLUX);
        latestPrice = currentPrice;
        int housePrice = calculateHousePrice(districtId);
        latestHousePrice = housePrice;
        latestDistrictId = districtId;
        return new StreetField(id, name, districtId, currentPrice, housePrice);
    }

    private int calculateHousePrice(String districtId) {
        if (latestDistrictId.equals(districtId)) {
            return latestHousePrice;
        } else {
            return latestHousePrice + HOUSE_PRICE_FLUX;
        }
    }
}

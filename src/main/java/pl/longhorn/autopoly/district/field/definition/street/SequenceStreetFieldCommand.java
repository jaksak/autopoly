package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

@Service
@RequiredArgsConstructor
public class SequenceStreetFieldCommand {

    private static final int INITIAL_PRICE = 50;
    private static final int PRICE_FLUX = 25;
    private final Randomizer randomizer;
    private int latestPrice = INITIAL_PRICE;

    public StreetField generate(String id, String name, String districtId) {
        int currentPrice = latestPrice + randomizer.nextInt(PRICE_FLUX);
        latestPrice = currentPrice;
        return new StreetField(id, name, districtId, currentPrice, currentPrice / 50 * 10 + 25, 0, false);
    }
}

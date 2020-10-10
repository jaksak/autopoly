package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.policy.view.ViewFieldPolicy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class StationViewFieldPolicy implements ViewFieldPolicy<StationField> {

    private final StationPriceService stationPriceService;
    private final StationLockFieldPolicy stationLockFieldPolicy;
    private final DistrictDetailsQuery districtDetailsQuery;

    @Override
    public AutopolyFieldDetailsView getView(StationField field) {
        return StationFieldView.builder()
                .id(field.getId())
                .name(field.getName())
                .buyingPrice(stationPriceService.getBuyingPrice())
                .currentRentPrice(stationPriceService.getRentPrice(field))
                .isLocked(stationLockFieldPolicy.isLocked(field))
                .lockPrice(stationLockFieldPolicy.getLockPrice(field))
                .rentPriceByDistrictBonus(getRentPriceByDistrictBonus())
                .build();
    }

    private List<Integer> getRentPriceByDistrictBonus() {
        var maxStationsInMap = districtDetailsQuery.get().getStationFieldIds().size();
        return IntStream.rangeClosed(0, maxStationsInMap)
                .map(stationPriceService::getRentPrice)
                .boxed()
                .collect(Collectors.toList());
    }
}

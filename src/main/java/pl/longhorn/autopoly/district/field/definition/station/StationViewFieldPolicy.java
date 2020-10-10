package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.policy.view.ViewFieldPolicy;

@Service
@RequiredArgsConstructor
public class StationViewFieldPolicy implements ViewFieldPolicy<StationField> {

    private final StationPriceService stationPriceService;
    private final StationLockFieldPolicy stationLockFieldPolicy;

    @Override
    public AutopolyFieldDetailsView getView(StationField field) {
        return StationFieldView.builder()
                .id(field.getId())
                .name(field.getName())
                .buyingPrice(stationPriceService.getBuyingPrice())
                .baseRentPrice(stationPriceService.getBaseRentPrice())
                .currentRentPrice(stationPriceService.getRentPrice(field))
                .isLocked(stationLockFieldPolicy.isLocked(field))
                .lockPrice(stationLockFieldPolicy.getLockPrice(field))
                .districtBonus(stationPriceService.getDistrictBonus())
                .build();
    }
}

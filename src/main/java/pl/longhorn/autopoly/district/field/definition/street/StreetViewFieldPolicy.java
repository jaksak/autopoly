package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.policy.view.ViewFieldPolicy;

@Service
@RequiredArgsConstructor
public class StreetViewFieldPolicy implements ViewFieldPolicy<StreetField> {

    private final StreetHouseFieldPolicy streetHouseFieldPolicy;
    private final StreetLockFieldPolicy streetLockFieldPolicy;
    private final StreetPriceService streetPriceService;

    @Override
    public AutopolyFieldDetailsView getView(StreetField field) {
        return StreetFieldView.builder()
                .id(field.getId())
                .districtId(field.getDistrictId())
                .name(field.getName())
                .price(streetPriceService.getBuyingPrice(field))
                .baseRentPrice(streetPriceService.getBaseRentPrice(field))
                .currentRentPrice(streetPriceService.getRentPrice(field))
                .districtBonus(streetPriceService.getFullDistrictBonus())
                .housePrice(streetHouseFieldPolicy.getHousePrice(field))
                .hotelPrice(streetHouseFieldPolicy.getHotelPrice(field))
                .houseLvl(streetHouseFieldPolicy.getHouseLvl(field))
                .isLocked(streetLockFieldPolicy.isLocked(field))
                .lockPrice(streetLockFieldPolicy.getLockPrice(field))
                .build();
    }
}

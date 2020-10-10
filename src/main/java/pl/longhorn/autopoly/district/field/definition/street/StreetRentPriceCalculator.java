package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StreetRentPriceCalculator {
    private final int rentPrice;
    private final int housePrice;
    private final int hotelPrice;
    private final int districtBonus;

    public int calculate(int houseLvl, boolean hasFullDistrict) {
        return rentPrice + getDistrictBonus(hasFullDistrict) + getHouseBonus(houseLvl) + getHotelBonus(houseLvl);
    }

    private int getHotelBonus(int houseLvl) {
        int hotelLevel = houseLvl - StreetConfig.MAX_HOUSES_WITHOUT_HOTEL;
        if (hotelLevel > 0) {
            return hotelLevel * hotelPrice;
        } else {
            return 0;
        }
    }

    private int getDistrictBonus(boolean hasFullDistrict) {
        return hasFullDistrict ? districtBonus : 0;
    }

    private int getHouseBonus(int houseLvl) {
        return Math.min(houseLvl, StreetConfig.MAX_HOUSES_WITHOUT_HOTEL) * housePrice;
    }
}
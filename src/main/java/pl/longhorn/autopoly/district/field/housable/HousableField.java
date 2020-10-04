package pl.longhorn.autopoly.district.field.housable;

import pl.longhorn.autopoly.district.field.AutopolyField;

public interface HousableField extends AutopolyField {
    int getHousePrice();

    int getHotelPrice();

    int getCurrentHousePrice();

    int getHouseLvl();

    boolean shouldIncreaseHouseLvl();

    boolean shouldDecreaseHouseLvl();

    HousableField increaseHouseLvl() throws IllegalHouseLvlOperationException;

    HousableField decreaseHouseLvl() throws IllegalHouseLvlOperationException;

    HousableField resetHouseLvl();
}

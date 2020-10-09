package pl.longhorn.autopoly.district.field.definition.street;

import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.IllegalHouseLvlOperationException;

public class StreetHouseFieldPolicy implements HouseFieldPolicy<StreetField> {
    @Override
    public boolean shouldHasHouse() {
        return true;
    }

    @Override
    public boolean shouldIncreaseHouseLvl(StreetField field) {
        return field.shouldIncreaseHouseLvl();
    }

    @Override
    public boolean shouldDecreaseHouseLvl(StreetField field) {
        return field.shouldDecreaseHouseLvl();
    }

    @Override
    public int getHousePrice(StreetField field) throws IllegalHouseLvlOperationException {
        return field.getHousePrice();
    }

    @Override
    public int getHotelPrice(StreetField field) throws IllegalHouseLvlOperationException {
        return field.getHotelPrice();
    }

    @Override
    public int getCurrentHousePrice(StreetField field) throws IllegalHouseLvlOperationException {
        return field.getCurrentHousePrice();
    }

    @Override
    public int getHouseLvl(StreetField field) throws IllegalHouseLvlOperationException {
        return field.getHouseLvl();
    }

    @Override
    public StreetField increaseHouseLvl(StreetField field) throws IllegalHouseLvlOperationException {
        return field.increaseHouseLvl();
    }

    @Override
    public StreetField decreaseHouseLvl(StreetField field) throws IllegalHouseLvlOperationException {
        return field.decreaseHouseLvl();
    }
}

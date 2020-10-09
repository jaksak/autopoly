package pl.longhorn.autopoly.district.field.definition.street;

import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.IllegalHouseLvlOperationException;

public class StreetHouseFieldPolicy implements HouseFieldPolicy<StreetField> {

    private static final int MAX_HOUSES_WITHOUT_HOTEL = 4;
    private static final int MAX_HOUSE_LVL = 5;

    @Override
    public boolean shouldHasHouse() {
        return true;
    }

    @Override
    public boolean shouldIncreaseHouseLvl(StreetField field) {
        return field.getHouseLvl() < MAX_HOUSE_LVL && !field.isLocked();
    }

    @Override
    public boolean shouldDecreaseHouseLvl(StreetField field) {
        return field.getHouseLvl() > 0;
    }

    @Override
    public int getHousePrice(StreetField field) throws IllegalHouseLvlOperationException {
        return field.getPriceToBuy() / 100 + 100;
    }

    @Override
    public int getHotelPrice(StreetField field) throws IllegalHouseLvlOperationException {
        return getHousePrice(field) + 50;
    }

    @Override
    public int getCurrentHousePrice(StreetField field) throws IllegalHouseLvlOperationException {
        return getHouseLvl(field) <= MAX_HOUSES_WITHOUT_HOTEL ? getHotelPrice(field) : getHousePrice(field);
    }

    @Override
    public int getHouseLvl(StreetField field) throws IllegalHouseLvlOperationException {
        return field.getHouseLvl();
    }

    @Override
    public StreetField increaseHouseLvl(StreetField field) throws IllegalHouseLvlOperationException {
        if (shouldIncreaseHouseLvl(field)) {
            return new StreetField(field.getId(), field.getDistrictId(), field.getName(), field.getPriceToBuy(), field.getInitialRentPrice(),
                    field.getHouseLvl() + 1, field.isLocked());
        } else {
            throw new IllegalHouseLvlOperationException();
        }
    }

    @Override
    public StreetField decreaseHouseLvl(StreetField field) throws IllegalHouseLvlOperationException {
        if (shouldDecreaseHouseLvl(field)) {
            return new StreetField(field.getId(), field.getDistrictId(), field.getName(), field.getPriceToBuy(), field.getInitialRentPrice(),
                    field.getHouseLvl() - 1, field.isLocked());
        } else {
            throw new IllegalHouseLvlOperationException();
        }
    }
}

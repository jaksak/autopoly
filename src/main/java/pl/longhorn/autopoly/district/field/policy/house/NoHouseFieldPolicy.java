package pl.longhorn.autopoly.district.field.policy.house;

import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Component
public class NoHouseFieldPolicy<T extends AutopolyField> implements HouseFieldPolicy<T> {
    @Override
    public boolean shouldHasHouse() {
        return false;
    }

    @Override
    public boolean shouldIncreaseHouseLvl(T field) {
        return false;
    }

    @Override
    public boolean shouldDecreaseHouseLvl(T field) {
        return false;
    }

    @Override
    public int getHousePrice(T field) throws IllegalHouseLvlOperationException {
        throw new IllegalHouseLvlOperationException();
    }

    @Override
    public int getHotelPrice(T field) throws IllegalHouseLvlOperationException {
        throw new IllegalHouseLvlOperationException();
    }

    @Override
    public int getCurrentHousePrice(T field) throws IllegalHouseLvlOperationException {
        throw new IllegalHouseLvlOperationException();
    }

    @Override
    public int getHouseLvl(T field) throws IllegalHouseLvlOperationException {
        throw new IllegalHouseLvlOperationException();
    }

    @Override
    public T increaseHouseLvl(T field) throws IllegalHouseLvlOperationException {
        throw new IllegalHouseLvlOperationException();
    }

    @Override
    public T decreaseHouseLvl(T field) throws IllegalHouseLvlOperationException {
        throw new IllegalHouseLvlOperationException();
    }
}

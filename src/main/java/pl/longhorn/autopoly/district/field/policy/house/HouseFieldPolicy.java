package pl.longhorn.autopoly.district.field.policy.house;

public interface HouseFieldPolicy<T> {
    boolean shouldHasHouse();

    boolean shouldIncreaseHouseLvl(T field);

    boolean shouldDecreaseHouseLvl(T field);

    int getHousePrice(T field) throws IllegalHouseLvlOperationException;

    int getHotelPrice(T field) throws IllegalHouseLvlOperationException;

    int getCurrentHousePrice(T field) throws IllegalHouseLvlOperationException;

    int getHouseLvl(T field) throws IllegalHouseLvlOperationException;

    T increaseHouseLvl(T field) throws IllegalHouseLvlOperationException;

    T decreaseHouseLvl(T field) throws IllegalHouseLvlOperationException;
}

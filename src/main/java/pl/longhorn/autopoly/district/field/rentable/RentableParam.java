package pl.longhorn.autopoly.district.field.rentable;


import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.player.Player;

@Builder
@Getter
public class RentableParam {
    private final String fieldId;
    private final String ownerId;
    private final Player player;
    private final boolean fieldHasOwner;
    private final boolean isCalledByOwner;
    private final int buyingPrice;
    private final int rentPrice;
    private final boolean isLocked;
}

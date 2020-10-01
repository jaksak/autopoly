package pl.longhorn.autopoly.district.field;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.player.Player;

@Builder
@Getter
public class AutopolyFieldActionParam {
    private final String ownerId;
    private final Player player;

    public boolean fieldHasOwner() {
        return ownerId != null;
    }

    public boolean isCalledByOwner() {
        return player.getId().equals(ownerId);
    }
}

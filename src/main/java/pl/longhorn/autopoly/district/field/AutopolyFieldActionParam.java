package pl.longhorn.autopoly.district.field;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.player.Player;

@Builder
@Getter
@Deprecated //TODO: remove it!
public class AutopolyFieldActionParam<T extends AutopolyField> {
    private final String ownerId;
    private final Player player;
    private final T field;

    public boolean fieldHasOwner() {
        return ownerId != null;
    }

    public boolean isCalledByOwner() {
        return player.getId().equals(ownerId);
    }
}

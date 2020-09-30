package pl.longhorn.autopoly.district.field;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.player.Player;

@Builder
@Getter
public class AutopolyFieldActionParam {
    private final boolean isOwner;
    private final boolean hasAnyOwner;
    private final Player player;
}

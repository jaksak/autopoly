package pl.longhorn.autopoly.player;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.longhorn.autopoly.player.state.PlayerState;
import pl.longhorn.autopoly.player.type.PlayerType;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Player {
    private final String id;
    private final String nick;
    @Setter(AccessLevel.PACKAGE)
    private int moneyAmount;
    @Setter(AccessLevel.PACKAGE)
    private String currentFieldId;
    private final PlayerType type;
    private final PlayerState state;
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private boolean isActive;

    public boolean shouldUseAutoAction() {
        return type.shouldUseAutoAction();
    }

    public boolean hasNegativeBalance() {
        return moneyAmount < 0;
    }
}

package pl.longhorn.autopoly.player;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.autopoly.player.state.PlayerState;
import pl.longhorn.autopoly.player.type.PlayerType;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
// TODO: class to large
public class Player {
    @Getter
    private final String id;
    private final String nick;
    private final int moneyAmount;
    private final String currentFieldId;
    private final List<String> ownedFields;
    private final PlayerType type;
    @Getter
    private final PlayerState state;

    public boolean shouldUseAutoAction() {
        return type.shouldUseAutoAction();
    }

    public PlayerView toView() {
        return PlayerView.builder()
                .id(id)
                .nick(nick)
                .moneyAmount(moneyAmount)
                .position(currentFieldId)
                .build();
    }
}

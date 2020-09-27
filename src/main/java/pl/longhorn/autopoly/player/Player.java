package pl.longhorn.autopoly.player;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.player.type.PlayerType;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Player {
    private final String id;
    private final String nick;
    private final int moneyAmount;
    private final String currentFieldId;
    private final List<String> ownedFields;
    private final PlayerType type;

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

package pl.longhorn.autopoly.player;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PlayerInBoard {
    @Getter
    private final List<Player> players;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String nextPlayerId;

    public Player getPlayerById(String playerId) {
        return players
                .stream()
                .filter(player -> playerId.equals(player.getId()))
                .findAny()
                .orElseThrow();
    }
}

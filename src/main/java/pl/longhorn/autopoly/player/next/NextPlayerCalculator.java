package pl.longhorn.autopoly.player.next;

import pl.longhorn.autopoly.player.Player;

import java.util.List;

public class NextPlayerCalculator {

    public String getNext(String latestPlayerId, List<Player> allPlayers) {
        boolean exceedLatest = false;
        for (Player player : allPlayers) {
            if (latestPlayerId.equals(player.getId())) {
                exceedLatest = true;
            } else if (exceedLatest) {
                return player.getId();
            }
        }
        return allPlayers.get(0).getId();
    }
}

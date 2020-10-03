package pl.longhorn.autopoly.player.next;

import pl.longhorn.autopoly.player.Player;

import java.util.ArrayList;
import java.util.List;

public class NextPlayerCalculator {

    public String getNext(String latestPlayerId, List<Player> allPlayers) {
        boolean exceedLatest = false;
        var playersToCheck = new ArrayList<>(allPlayers);
        playersToCheck.addAll(allPlayers);
        for (Player player : playersToCheck) {
            if (latestPlayerId.equals(player.getId())) {
                exceedLatest = true;
            } else if (exceedLatest && player.isActive() && !player.getId().equals(latestPlayerId)) {
                return player.getId();
            }
        }
        return null;
    }
}

package pl.longhorn.autopoly.player;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
class PlayerRepository {

    private final List<Player> players = new LinkedList<>();

    public void save(Player player) {
        players.add(player);
    }

    public void clear() {
        players.clear();
    }

    public List<Player> getAll() {
        return players;
    }
}

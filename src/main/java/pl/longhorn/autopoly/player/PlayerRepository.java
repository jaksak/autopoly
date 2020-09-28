package pl.longhorn.autopoly.player;

import org.springframework.stereotype.Repository;

@Repository
class PlayerRepository {

    private final Object monitor = new Object();

    private PlayerInBoard playerInBoard = null;

    public void save(PlayerInBoard playerInBoard) {
        synchronized (monitor) {
            this.playerInBoard = playerInBoard;
        }
    }

    public void clear() {
        synchronized (monitor) {
            playerInBoard = null;
        }
    }

    public PlayerInBoard get() {
        synchronized (monitor) {
            return playerInBoard;
        }
    }
}

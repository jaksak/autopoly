package pl.longhorn.autopoly.board;

import org.springframework.stereotype.Repository;

@Repository
class BoardRepository {

    private final Object monitor = new Object();
    private Board board;

    public Board get() {
        synchronized (monitor) {
            return board;
        }
    }

    public void save(Board board) {
        synchronized (monitor) {
            this.board = board;
        }
    }
}

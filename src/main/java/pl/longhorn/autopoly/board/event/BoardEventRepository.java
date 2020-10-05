package pl.longhorn.autopoly.board.event;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class BoardEventRepository {

    private final Object monitor = new Object();
    private List<BoardEvent> events = new LinkedList<>();

    public List<BoardEvent> get() {
        synchronized (monitor) {
            return new LinkedList<>(events);
        }
    }

    public void save(List<BoardEvent> events) {
        synchronized (monitor) {
            this.events = events;
        }
    }

    public void clear() {
        synchronized (monitor) {
            events = new LinkedList<>();
        }
    }
}

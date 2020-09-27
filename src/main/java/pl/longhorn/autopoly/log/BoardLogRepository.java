package pl.longhorn.autopoly.log;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Queue;

@Repository
class BoardLogRepository {

    private final Object monitor = new Object();
    private Queue<BoardLog> logs = new LinkedList<>();

    public Queue<BoardLog> getAll() {
        synchronized (monitor) {
            return logs;
        }
    }

    public void replace(Queue<BoardLog> replacement) {
        synchronized (monitor) {
            logs = replacement;
        }
    }
}

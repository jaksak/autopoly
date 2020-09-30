package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.event.BoardEvent;

import java.util.LinkedList;
import java.util.List;

@Service
// TODO: events independent from board!
@RequiredArgsConstructor
public class AddBoardEventsCommand {

    private final BoardAccessor boardAccessor;
    private final BoardRepository boardRepository;

    public void add(List<BoardEvent> newEvents) {
        var board = boardAccessor.getBoard();
        var allEvents = new LinkedList<>(board.getUnconsideredEvents());
        allEvents.addAll(newEvents);
        board.setUnconsideredEvents(allEvents);
        boardRepository.save(board);
    }
}

package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBoardEventCommand {

    private final BoardRepository boardRepository;

    public void delete(String eventId) {
        var board = boardRepository.get();
        var events = board.getUnconsideredEvents();
        board.getUnconsideredEvents().removeIf(event -> event.getId().equals(eventId));
        board.setUnconsideredEvents(events);
    }
}

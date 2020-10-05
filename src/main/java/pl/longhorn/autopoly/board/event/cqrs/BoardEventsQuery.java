package pl.longhorn.autopoly.board.event.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.board.event.BoardEventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardEventsQuery {

    private final BoardEventRepository eventRepository;

    public List<BoardEvent> get() {
        return eventRepository.get();
    }
}

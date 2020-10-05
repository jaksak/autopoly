package pl.longhorn.autopoly.board.event.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.board.event.BoardEventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddBoardEventsCommand {

    private final BoardEventRepository eventRepository;

    public void add(List<BoardEvent> newEvents) {
        var allEvents = eventRepository.get();
        allEvents.addAll(newEvents);
        eventRepository.save(allEvents);
    }
}

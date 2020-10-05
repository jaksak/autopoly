package pl.longhorn.autopoly.board.event.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.event.BoardEventRepository;

@Service
@RequiredArgsConstructor
public class DeleteBoardEventCommand {

    private final BoardEventRepository eventRepository;

    public void delete(String eventId) {
        var events = eventRepository.get();
        events.removeIf(event -> event.getId().equals(eventId));
        eventRepository.save(events);
    }
}

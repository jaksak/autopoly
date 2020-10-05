package pl.longhorn.autopoly.board.event.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.event.BoardEventRepository;

@Service
@RequiredArgsConstructor
public class ClearBoardEventsCommand {

    private final BoardEventRepository eventRepository;

    public void clear() {
        eventRepository.clear();
    }
}

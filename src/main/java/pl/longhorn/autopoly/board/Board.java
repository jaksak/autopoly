package pl.longhorn.autopoly.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.longhorn.autopoly.board.event.BoardEvent;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class Board {
    private final String id;
    @Builder.Default
    @Setter(AccessLevel.PACKAGE)
    private Queue<BoardEvent> unconsideredEvents = new LinkedList<>();
}



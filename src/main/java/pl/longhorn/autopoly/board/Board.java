package pl.longhorn.autopoly.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class Board {
    private final String id;
    @Builder.Default
    private final Queue<BoardEvent> unconsideredEvents = new LinkedList<>();
}



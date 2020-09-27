package pl.longhorn.autopoly.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Builder(access = AccessLevel.PROTECTED)
public class Board {
    @Getter
    private final String id;
    @Builder.Default
    private final Queue<BoardEvent> unconsideredEvent = new LinkedList<>();
}



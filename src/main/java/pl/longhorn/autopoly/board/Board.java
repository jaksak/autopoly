package pl.longhorn.autopoly.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder(access = AccessLevel.PROTECTED)
public class Board {
    @Getter
    private final String id;
}



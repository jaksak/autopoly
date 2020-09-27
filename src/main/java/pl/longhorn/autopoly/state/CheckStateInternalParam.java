package pl.longhorn.autopoly.state;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.board.Board;

@Builder
@Getter
class CheckStateInternalParam {
    private final Board board;
}

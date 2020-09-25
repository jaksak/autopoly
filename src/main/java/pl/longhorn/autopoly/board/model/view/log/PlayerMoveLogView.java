package pl.longhorn.autopoly.board.model.view.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerMoveLogView implements BoardLogView {
    private final BoardLogType type = BoardLogType.PLAYER_MOVE;
    private final String playerId;
    private final int currentPosition;
}

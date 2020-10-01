package pl.longhorn.autopoly.board.event;

import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.player.Player;

public interface BoardEvent {
    String getPlayerId();

    BoardActionResult react(Board board, Player player);
}

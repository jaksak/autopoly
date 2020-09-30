package pl.longhorn.autopoly.player.state;

import pl.longhorn.autopoly.board.BoardActionResult;
import pl.longhorn.autopoly.player.Player;

public interface PlayerState {

    BoardActionResult autoProcessAction(Player player);
}

package pl.longhorn.autopoly.player.state;

import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.player.Player;

public interface PlayerState {

    BoardActionResult autoProcessAction(Player player);
}

package pl.longhorn.autopoly.board;

import pl.longhorn.autopoly.player.Player;

public interface BoardEvent {
    String getPlayerId();

    BoardActionResult react(Board board, Player player);
}

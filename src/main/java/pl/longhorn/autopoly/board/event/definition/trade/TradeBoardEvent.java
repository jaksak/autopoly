package pl.longhorn.autopoly.board.event.definition.trade;

import lombok.Getter;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.player.Player;

@Getter
public class TradeBoardEvent implements BoardEvent {
    private String id;
    private String playerId;

    @Override
    public BoardActionResult react(Board board, Player player) {
        return null;
    }
}

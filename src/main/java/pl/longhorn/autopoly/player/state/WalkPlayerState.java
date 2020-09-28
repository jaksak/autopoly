package pl.longhorn.autopoly.player.state;

import pl.longhorn.autopoly.board.BoardActionResult;
import pl.longhorn.autopoly.player.AutoProcessActionParam;
import pl.longhorn.autopoly.player.Player;

public class WalkPlayerState implements PlayerState {

    @Override
    public BoardActionResult autoProcessAction(AutoProcessActionParam param, Player player) {
        // TODO: implement me!
        return BoardActionResult.builder()
                .build();
    }
}

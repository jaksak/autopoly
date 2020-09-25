package pl.longhorn.autopoly.board.model.view;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.board.model.view.log.BoardLogView;
import pl.longhorn.autopoly.board.model.view.player.PlayerDetails;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class BoardStateView implements Serializable {
    private final List<PlayerDetails> players;
    private final List<BoardLogView> logs;
}

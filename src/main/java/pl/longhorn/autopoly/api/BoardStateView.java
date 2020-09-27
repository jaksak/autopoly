package pl.longhorn.autopoly.api;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.log.BoardLogView;
import pl.longhorn.autopoly.player.PlayerView;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class BoardStateView implements Serializable {
    private final List<PlayerView> players;
    private final List<BoardLogView> logs;
}

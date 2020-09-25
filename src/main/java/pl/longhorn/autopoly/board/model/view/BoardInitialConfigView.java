package pl.longhorn.autopoly.board.model.view;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.board.model.view.field.AutopolyFieldDetails;
import pl.longhorn.autopoly.board.model.view.player.PlayerDetails;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class BoardInitialConfigView implements Serializable {
    private final List<AutopolyFieldDetails> fields;
    private final List<PlayerDetails> players;
}

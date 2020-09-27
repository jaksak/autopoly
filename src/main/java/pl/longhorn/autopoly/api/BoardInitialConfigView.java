package pl.longhorn.autopoly.api;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.player.PlayerView;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class BoardInitialConfigView implements Serializable {
    private final List<AutopolyFieldDetailsView> fields;
    private final List<PlayerView> players;
}

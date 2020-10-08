package pl.longhorn.autopoly.player.ownership;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlayerOwnership {
    private final String playerId;
    private final List<String> fieldIds;
}

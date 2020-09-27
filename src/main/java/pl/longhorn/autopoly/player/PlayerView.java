package pl.longhorn.autopoly.player;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class PlayerView implements Serializable {
    private final String id;
    private final String nick;
    private final int moneyAmount;
    private final String position;
}

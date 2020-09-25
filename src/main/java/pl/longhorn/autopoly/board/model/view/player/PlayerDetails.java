package pl.longhorn.autopoly.board.model.view.player;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class PlayerDetails implements Serializable {
    private final String id;
    private final String name;
    private final int moneyAmount;
    private final String position;
}

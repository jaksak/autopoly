package pl.longhorn.autopoly.log.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.autopoly.dice.DiceRoll;

@Getter
@AllArgsConstructor
public class WalkPlayerMoveReason implements PlayerMoveReason {
    private final String description = "walk";
    private final DiceRoll diceRoll;
}

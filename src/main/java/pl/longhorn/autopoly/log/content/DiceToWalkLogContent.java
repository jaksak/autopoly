package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DiceToWalkLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.DICE_WALK;
    private final String playerId;
    private final int dice1Value;
    private final int dice2Value;
}

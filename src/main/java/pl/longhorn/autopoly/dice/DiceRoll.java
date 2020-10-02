package pl.longhorn.autopoly.dice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DiceRoll {
    protected final int dice1Value;
    protected final int dice2Value;

    public int sum() {
        return dice1Value + dice2Value;
    }

    public boolean hasCombo() {
        return this.dice1Value == dice2Value;
    }
}

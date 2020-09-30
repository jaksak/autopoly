package pl.longhorn.autopoly.dice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DiceRoll {
    protected final int dice1;
    protected final int dice2;

    public int sum() {
        return dice1 + dice2;
    }

    public boolean hasCombo() {
        return this.dice1 == dice2;
    }
}

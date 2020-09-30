package pl.longhorn.autopoly.dice;

public class DiceRollWithCombo extends DiceRoll {
    private DiceCombo combo;

    DiceRollWithCombo(int dice1, int dice2) {
        super(dice1, dice2);
    }

    DiceRollWithCombo(int dice1, int dice2, DiceCombo combo) {
        super(dice1, dice2);
        this.combo = combo;
    }

    public boolean shouldPunish() {
        return combo != null && combo.getComboAmount() > 2;
    }
}

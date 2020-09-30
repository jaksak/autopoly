package pl.longhorn.autopoly.dice;

import org.springframework.stereotype.Repository;

@Repository
public class DiceComboRepository {

    private final Object monitor = new Object();
    private DiceCombo diceCombo;

    public DiceCombo get() {
        synchronized (monitor) {
            return diceCombo;
        }
    }

    public void save(DiceCombo combo) {
        synchronized (monitor) {
            diceCombo = combo;
        }
    }

    public void clear() {
        synchronized (monitor) {
            diceCombo = null;
        }
    }
}

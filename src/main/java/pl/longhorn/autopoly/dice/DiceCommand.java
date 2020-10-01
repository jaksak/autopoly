package pl.longhorn.autopoly.dice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

@Service
@RequiredArgsConstructor
public class DiceCommand {

    private final DiceComboRepository diceComboRepository;
    private final Randomizer randomizer;

    public DiceRollWithCombo rollWithCombo(String boardId, String playerId) {
        int dice1Value = randomizer.nextInt(1, 6);
        int dice2Value = randomizer.nextInt(1, 6);
        DiceRoll roll = new DiceRoll(dice1Value, dice2Value);
        if (roll.hasCombo()) {
            return prepareCombo(dice1Value, dice2Value, boardId, playerId);
        } else {
            diceComboRepository.clear();
            return new DiceRollWithCombo(dice1Value, dice2Value);
        }
    }

    private DiceRollWithCombo prepareCombo(int dice1Value, int dice2Value, String boardId, String playerId) {
        var latestCombo = diceComboRepository.get();
        if (latestCombo != null && latestCombo.metaDataEquals(boardId, playerId)) {
            var currentCombo = latestCombo.increase();
            diceComboRepository.save(currentCombo);
            return new DiceRollWithCombo(dice1Value, dice2Value, currentCombo);
        } else {
            var currentCombo = DiceCombo.first(boardId, playerId);
            diceComboRepository.save(currentCombo);
            return new DiceRollWithCombo(dice1Value, dice2Value, currentCombo);
        }
    }
}

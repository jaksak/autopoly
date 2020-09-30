package pl.longhorn.autopoly.dice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class DiceCombo {
    private final String boardId;
    private final String playerId;
    @Getter
    private final int comboAmount;

    protected static DiceCombo first(String boardId, String playerId) {
        return new DiceCombo(boardId, playerId, 0);
    }

    public boolean metaDataEquals(String boardId, String playerId) {
        return this.boardId.equals(boardId) && this.playerId.equals(playerId);
    }

    protected DiceCombo increase() {
        return new DiceCombo(boardId, playerId, comboAmount + 1);
    }
}

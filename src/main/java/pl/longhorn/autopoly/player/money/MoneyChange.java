package pl.longhorn.autopoly.player.money;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoneyChange {
    private final String playerId;
    private final int moneyAmount;
}

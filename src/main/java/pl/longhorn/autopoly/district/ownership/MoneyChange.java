package pl.longhorn.autopoly.district.ownership;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoneyChange {
    private final String playerId;
    private final int moneyAmount;
}

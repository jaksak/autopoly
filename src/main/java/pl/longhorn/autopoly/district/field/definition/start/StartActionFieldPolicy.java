package pl.longhorn.autopoly.district.field.definition.start;

import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.money.MoneyChange;

public class StartActionFieldPolicy implements ActionFieldPolicy<StartField> {

    @Override
    public BoardActionResult countActionAfterPlayerStay(StartField field, Player player) {
        return BoardActionResult.builder()
                .moneyChange(new MoneyChange(player.getId(), 50))
                .build();
    }
}

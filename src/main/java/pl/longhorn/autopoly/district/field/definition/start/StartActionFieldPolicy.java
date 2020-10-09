package pl.longhorn.autopoly.district.field.definition.start;

import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.player.money.MoneyChange;

public class StartActionFieldPolicy implements ActionFieldPolicy<StartField> {

    @Override
    public BoardActionResult countActionAfterPlayerStay(AutopolyFieldActionParam<StartField> actionParam) {
        return BoardActionResult.builder()
                .moneyChange(new MoneyChange(actionParam.getPlayer().getId(), 50))
                .build();
    }
}

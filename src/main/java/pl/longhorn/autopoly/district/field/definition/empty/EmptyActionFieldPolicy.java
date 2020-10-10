package pl.longhorn.autopoly.district.field.definition.empty;

import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;
import pl.longhorn.autopoly.player.Player;

public class EmptyActionFieldPolicy implements ActionFieldPolicy<EmptyField> {
    @Override
    public BoardActionResult countActionAfterPlayerStay(EmptyField field, Player player) {
        return BoardActionResult.builder()
                .build();
    }
}

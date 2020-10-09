package pl.longhorn.autopoly.district.field.definition.empty;

import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.policy.action.ActionFieldPolicy;

public class EmptyActionFieldPolicy implements ActionFieldPolicy<EmptyField> {
    @Override
    public BoardActionResult countActionAfterPlayerStay(AutopolyFieldActionParam<EmptyField> actionParam) {
        return BoardActionResult.builder()
                .build();
    }
}

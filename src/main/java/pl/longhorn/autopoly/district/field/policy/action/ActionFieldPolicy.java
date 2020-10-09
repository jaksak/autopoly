package pl.longhorn.autopoly.district.field.policy.action;

import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;

public interface ActionFieldPolicy<T extends AutopolyField> {

    BoardActionResult countActionAfterPlayerStay(AutopolyFieldActionParam<T> actionParam);
}

package pl.longhorn.autopoly.district.field;

import pl.longhorn.autopoly.action.BoardActionResult;

public interface AutopolyField {
    String getId();

    AutopolyFieldDetailsView toView();

    BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam);

    AutopolyField reset();
}

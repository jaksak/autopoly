package pl.longhorn.autopoly.district.field;

import pl.longhorn.autopoly.action.BoardActionResult;

// TODO: package field
public interface AutopolyField {
    String getId();

    AutopolyFieldDetailsView toView();

    BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam);
}

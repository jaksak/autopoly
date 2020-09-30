package pl.longhorn.autopoly.district.field;

import pl.longhorn.autopoly.board.BoardActionResult;

// TODO: package field
public interface AutopolyField {
    String getId();

    AutopolyFieldDetailsView toView();

    BoardActionResult afterPlayerStay(AutopolyFieldActionParam actionParam);
}

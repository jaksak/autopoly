package pl.longhorn.autopoly.board.model.view.log;

import java.io.Serializable;

public interface BoardLogView extends Serializable {
    BoardLogType getType();
}

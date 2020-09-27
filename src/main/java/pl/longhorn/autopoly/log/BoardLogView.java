package pl.longhorn.autopoly.log;

import java.io.Serializable;

public interface BoardLogView extends Serializable {
    String getId();

    BoardLogType getType();
}

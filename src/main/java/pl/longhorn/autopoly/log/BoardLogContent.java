package pl.longhorn.autopoly.log;

import java.io.Serializable;

public interface BoardLogContent extends Serializable {
    String getId();

    BoardLogType getType();
}

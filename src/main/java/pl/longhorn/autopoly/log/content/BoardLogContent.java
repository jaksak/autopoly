package pl.longhorn.autopoly.log.content;

import java.io.Serializable;

public interface BoardLogContent extends Serializable {
    String getId();

    BoardLogType getType();
}

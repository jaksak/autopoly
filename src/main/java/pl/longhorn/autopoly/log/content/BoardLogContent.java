package pl.longhorn.autopoly.log.content;

import java.io.Serializable;

public interface BoardLogContent extends Serializable {
    BoardLogType getType();
}

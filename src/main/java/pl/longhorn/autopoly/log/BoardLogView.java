package pl.longhorn.autopoly.log;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.log.content.BoardLogContent;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class BoardLogView implements Serializable {
    private final String id;
    private final BoardLogContent content;
}

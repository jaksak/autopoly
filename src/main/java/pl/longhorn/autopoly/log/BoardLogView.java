package pl.longhorn.autopoly.log;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class BoardLogView implements Serializable {
    private final String id;
    private final BoardLogContent content;
}

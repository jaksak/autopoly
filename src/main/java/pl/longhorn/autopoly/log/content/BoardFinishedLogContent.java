package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardFinishedLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.GAME_FINISH;
    private final String boardId;
    private final String winnerId;
}

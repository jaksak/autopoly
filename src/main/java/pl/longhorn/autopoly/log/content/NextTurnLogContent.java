package pl.longhorn.autopoly.log.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NextTurnLogContent implements BoardLogContent {
    private final BoardLogType type = BoardLogType.NEXT_TURN;
    private final String playerId;
}

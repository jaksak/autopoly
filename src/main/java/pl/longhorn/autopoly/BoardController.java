package pl.longhorn.autopoly;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.longhorn.autopoly.board.BoardQuery;
import pl.longhorn.autopoly.board.api.BoardInitialConfigView;
import pl.longhorn.autopoly.board.api.BoardStateView;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.log.BoardLog;
import pl.longhorn.autopoly.log.BoardLogAfterIdQuery;
import pl.longhorn.autopoly.log.BoardLogQuery;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayersQuery;
import pl.longhorn.autopoly.state.CheckStateCommand;

import java.util.stream.Collectors;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayersQuery playersQuery;
    private final CheckStateCommand checkStateCommand;
    private final BoardQuery boardQuery;
    private final BoardLogQuery boardLogQuery;
    private final BoardLogAfterIdQuery boardLogAfterIdQuery;

    @GetMapping("state")
    public BoardStateView getBoardState(@RequestParam String boardId, @RequestParam(required = false) String logsAfter) {
        checkStateCommand.checkState();
        var logs = logsAfter == null ? boardLogQuery.getByBoardId(boardId) : boardLogAfterIdQuery.getLogByBoardIdAndAfter(boardId, logsAfter);
        return BoardStateView.builder()
                .logs(logs.stream().map(BoardLog::toView).collect(Collectors.toList()))
                .players(playersQuery.get().stream().map(Player::toView).collect(Collectors.toList()))
                .build();
    }

    @GetMapping("config")
    public BoardInitialConfigView getInitConfig() {
        checkStateCommand.checkState();
        var board = boardQuery.get();
        if (board == null) {
            return null;
        } else {
            return BoardInitialConfigView.builder()
                    .boardId(board.getId())
                    .fields(districtDetailsQuery.get().getFieldByBoardOrder().stream().map(AutopolyField::toView).collect(Collectors.toList()))
                    .players(playersQuery.get().stream().map(Player::toView).collect(Collectors.toList()))
                    .build();
        }
    }
}

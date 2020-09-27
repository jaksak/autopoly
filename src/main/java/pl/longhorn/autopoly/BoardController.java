package pl.longhorn.autopoly;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.longhorn.autopoly.api.BoardInitialConfigView;
import pl.longhorn.autopoly.api.BoardStateView;
import pl.longhorn.autopoly.board.BoardQuery;
import pl.longhorn.autopoly.field.AutopolyField;
import pl.longhorn.autopoly.field.DistrictDetailsQuery;
import pl.longhorn.autopoly.log.PlayerBuyLogView;
import pl.longhorn.autopoly.log.PlayerMoveLogView;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayersQuery;
import pl.longhorn.autopoly.state.CheckStateCommand;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayersQuery playersQuery;
    private final CheckStateCommand checkStateCommand;
    private final BoardQuery boardQuery;

    @GetMapping("state")
    public BoardStateView getBoardState(@RequestParam String boardId, @RequestParam(required = false) String logsAfter) {
        System.out.println(boardId + " " + logsAfter); // TODO: use it!
        checkStateCommand.checkState();
        return BoardStateView.builder()
                .logs(List.of(
                        new PlayerMoveLogView("A", "0", "1"),
                        new PlayerBuyLogView("B", "0", "1")
                ))
                .players(
                        playersQuery.get().stream().map(Player::toView).collect(Collectors.toList()))
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
                    .fields(
                            districtDetailsQuery.get().getFieldByBoardOrder().stream().map(AutopolyField::toView).collect(Collectors.toList())
                    )
                    .players(
                            playersQuery.get().stream().map(Player::toView).collect(Collectors.toList())
                    )
                    .build();
        }
    }
}

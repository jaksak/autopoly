package pl.longhorn.autopoly;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.longhorn.autopoly.board.BoardAccessor;
import pl.longhorn.autopoly.board.api.BoardInitialConfigView;
import pl.longhorn.autopoly.board.api.BoardStateView;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.AutopolyFieldDetailsView;
import pl.longhorn.autopoly.district.field.cqrs.FieldViewQuery;
import pl.longhorn.autopoly.log.BoardLog;
import pl.longhorn.autopoly.log.BoardLogAfterIdQuery;
import pl.longhorn.autopoly.log.BoardLogQuery;
import pl.longhorn.autopoly.player.NextPlayerQuery;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayerView;
import pl.longhorn.autopoly.player.PlayersQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;
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
    private final BoardAccessor boardAccessor;
    private final BoardLogQuery boardLogQuery;
    private final BoardLogAfterIdQuery boardLogAfterIdQuery;
    private final NextPlayerQuery nextPlayerQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldViewQuery fieldViewQuery;

    @GetMapping("state")
    public BoardStateView getBoardState(@RequestParam String boardId, @RequestParam(required = false) String logsAfter) {
        checkStateCommand.checkState();
        var logs = logsAfter == null ? boardLogQuery.getByBoardId(boardId) : boardLogAfterIdQuery.getLogByBoardIdAndAfter(boardId, logsAfter);
        return BoardStateView.builder()
                .logs(logs.stream().map(BoardLog::toView).collect(Collectors.toList()))
                .players(playersQuery.get().stream().map(this::toView).collect(Collectors.toList()))
                .build();
    }

    @GetMapping("config")
    public BoardInitialConfigView getInitConfig() {
        var board = boardAccessor.getBoard();
        return BoardInitialConfigView.builder()
                .boardId(board.getId())
                .fields(getFieldsView())
                .players(playersQuery.get().stream().map(this::toView).collect(Collectors.toList()))
                .currentPlayerId(nextPlayerQuery.get().map(Player::getId).orElse(null))
                .build();
    }

    private List<AutopolyFieldDetailsView> getFieldsView() {
        return districtDetailsQuery.get().getFieldByBoardOrder().stream()
                .map(fieldViewQuery::get)
                .collect(Collectors.toList());
    }

    private PlayerView toView(Player player) {
        return PlayerView.builder()
                .id(player.getId())
                .nick(player.getNick())
                .moneyAmount(player.getMoneyAmount())
                .position(player.getCurrentFieldId())
                .ownedFieldIds(playerOwnershipQuery.get(player.getId()))
                .isActive(player.isActive())
                .build();
    }
}

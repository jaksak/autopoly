package pl.longhorn.autopoly;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.longhorn.autopoly.board.BoardService;
import pl.longhorn.autopoly.board.model.view.BoardInitialConfigView;
import pl.longhorn.autopoly.board.model.view.BoardStateView;
import pl.longhorn.autopoly.board.model.view.log.PlayerBuyLogView;
import pl.longhorn.autopoly.board.model.view.log.PlayerMoveLogView;
import pl.longhorn.autopoly.board.model.view.player.PlayerDetails;
import pl.longhorn.autopoly.field.AutopolyField;
import pl.longhorn.autopoly.field.DistrictDetailsCommand;
import pl.longhorn.autopoly.field.DistrictDetailsQuery;
import pl.longhorn.autopoly.street.name.RandomStreetNameQuery;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final RandomStreetNameQuery randomStreetNameQuery;
    private final DistrictDetailsQuery districtDetailsQuery;
    private final DistrictDetailsCommand districtDetailsCommand;

    @GetMapping("state")
    public BoardStateView getBoardState(@RequestParam(required = false) String logsAfter) {
        boardService.checkState();
        var board = boardService.getBoard();
        return BoardStateView.builder()
                .logs(List.of(
                        new PlayerMoveLogView("A", "0", "1"),
                        new PlayerBuyLogView("B", "0", "1")
                ))
                .players(List.of(
                        PlayerDetails.builder()
                                .id("0")
                                .name("Jankow")
                                .moneyAmount(5000)
                                .position("1")
                                .build(),
                        PlayerDetails.builder()
                                .id("1")
                                .name("Juliusz")
                                .moneyAmount(3699)
                                .position("1")
                                .build()
                ))
                .build();
    }

    @GetMapping("config")
    public BoardInitialConfigView getInitConfig() {
        var board = boardService.getBoard();
        var districtDetails = districtDetailsQuery.get();
        if (districtDetails == null) {
            districtDetails = districtDetailsCommand.prepareFields();
        }
        return BoardInitialConfigView.builder()
                .fields(
                        districtDetails.getFieldByBoardOrder().stream().map(AutopolyField::toView).collect(Collectors.toList())
                )
                .players(List.of(
                        PlayerDetails.builder()
                                .id("0")
                                .name("Jankow")
                                .moneyAmount(5000)
                                .position("0")
                                .build(),
                        PlayerDetails.builder()
                                .id("1")
                                .name("Juliusz")
                                .moneyAmount(3699)
                                .position("1")
                                .build()
                ))
                .build();
    }
}

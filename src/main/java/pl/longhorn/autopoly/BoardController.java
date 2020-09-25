package pl.longhorn.autopoly;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.longhorn.autopoly.board.BoardService;
import pl.longhorn.autopoly.board.model.view.BoardInitialConfigView;
import pl.longhorn.autopoly.board.model.view.BoardStateView;
import pl.longhorn.autopoly.board.model.view.field.ParcelFieldView;
import pl.longhorn.autopoly.board.model.view.field.StartFieldView;
import pl.longhorn.autopoly.board.model.view.log.PlayerBuyLogView;
import pl.longhorn.autopoly.board.model.view.log.PlayerMoveLogView;
import pl.longhorn.autopoly.board.model.view.player.PlayerDetails;
import pl.longhorn.autopoly.street.StreetNameService;

import java.util.List;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final StreetNameService streetNameService;

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
        return BoardInitialConfigView.builder()
                .fields(List.of(
                        new StartFieldView("0"),
                        new ParcelFieldView("1", streetNameService.getRandom(), 500),
                        new ParcelFieldView("2", streetNameService.getRandom(), 560),
                        new ParcelFieldView("3", streetNameService.getRandom(), 550),
                        new ParcelFieldView("4", streetNameService.getRandom(), 540),
                        new ParcelFieldView("5", streetNameService.getRandom(), 530),
                        new ParcelFieldView("6", streetNameService.getRandom(), 520),
                        new ParcelFieldView("7", streetNameService.getRandom(), 510)

                ))
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

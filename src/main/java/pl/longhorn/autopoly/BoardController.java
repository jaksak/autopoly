package pl.longhorn.autopoly;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.longhorn.autopoly.board.BoardService;
import pl.longhorn.autopoly.board.model.view.BoardInitialConfigView;
import pl.longhorn.autopoly.board.model.view.BoardStateView;
import pl.longhorn.autopoly.board.model.view.field.ParcelFieldView;
import pl.longhorn.autopoly.board.model.view.field.StartFieldView;
import pl.longhorn.autopoly.board.model.view.player.PlayerDetails;

import java.util.List;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("state")
    public BoardStateView getBoardState() {
        boardService.checkState();
        var board = boardService.getBoard();
        return BoardStateView.builder()
                .logs(List.of())
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

    @GetMapping("config")
    public BoardInitialConfigView getInitConfig() {
        var board = boardService.getBoard();
        return BoardInitialConfigView.builder()
                .fields(List.of(
                        new StartFieldView("0"),
                        new ParcelFieldView("1", "ulica Kielecka", 500)

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

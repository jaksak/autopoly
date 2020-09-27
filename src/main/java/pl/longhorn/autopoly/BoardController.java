package pl.longhorn.autopoly;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.longhorn.autopoly.api.BoardInitialConfigView;
import pl.longhorn.autopoly.api.BoardStateView;
import pl.longhorn.autopoly.field.AutopolyField;
import pl.longhorn.autopoly.field.DistrictDetailsAccessor;
import pl.longhorn.autopoly.log.PlayerBuyLogView;
import pl.longhorn.autopoly.log.PlayerMoveLogView;
import pl.longhorn.autopoly.player.PlayerView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("board/")
@RequiredArgsConstructor
public class BoardController {

    private final DistrictDetailsAccessor districtDetailsAccessor;

    @GetMapping("state")
    public BoardStateView getBoardState(@RequestParam(required = false) String logsAfter) {
//        boardService.checkState();
//        var board = boardService.getBoard();
        return BoardStateView.builder()
                .logs(List.of(
                        new PlayerMoveLogView("A", "0", "1"),
                        new PlayerBuyLogView("B", "0", "1")
                ))
                .players(List.of(
                        PlayerView.builder()
                                .id("0")
                                .name("Jankow")
                                .moneyAmount(5000)
                                .position("1")
                                .build(),
                        PlayerView.builder()
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
//        var board = boardService.getBoard();
        return BoardInitialConfigView.builder()
                .fields(
                        districtDetailsAccessor.get().getFieldByBoardOrder().stream().map(AutopolyField::toView).collect(Collectors.toList())
                )
                .players(List.of(
                        PlayerView.builder()
                                .id("0")
                                .name("Jankow")
                                .moneyAmount(5000)
                                .position("0")
                                .build(),
                        PlayerView.builder()
                                .id("1")
                                .name("Juliusz")
                                .moneyAmount(3699)
                                .position("1")
                                .build()
                ))
                .build();
    }
}

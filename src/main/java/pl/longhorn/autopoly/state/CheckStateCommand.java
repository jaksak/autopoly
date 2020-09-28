package pl.longhorn.autopoly.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.BoardCommand;
import pl.longhorn.autopoly.board.BoardQuery;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.player.AutoProcessActionParam;
import pl.longhorn.autopoly.player.NextPlayerQuery;

@Service
@RequiredArgsConstructor
public class CheckStateCommand {

    private static final int MAX_ACTION_AMOUNT = 15;

    private final BoardQuery boardQuery;
    private final BoardCommand boardCommand;
    private final NextPlayerQuery nextPlayerQuery;
    private final DistrictDetailsQuery districtDetailsQuery;

    public synchronized void checkState() {
        // TODO: checking date!
        for (int i = 0; i < MAX_ACTION_AMOUNT; i++) {
            var currentPlayer = nextPlayerQuery.get();
            if (currentPlayer.shouldUseAutoAction()) {
                var param = getParam();

            }
        }
    }

    private AutoProcessActionParam getParam() {
        return AutoProcessActionParam.builder()
                .board(getBoard())
                .districtDetails(districtDetailsQuery.get())
                .build();
    }

    private Board getBoard() {
        var result = boardQuery.get();
        if (result == null) {
            return boardCommand.create();
        } else {
            return result;
        }
    }
}

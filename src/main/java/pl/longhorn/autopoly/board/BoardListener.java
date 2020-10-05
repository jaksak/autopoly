package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.board.event.cqrs.ClearBoardEventsCommand;
import pl.longhorn.autopoly.district.ClearDistrictDetailsCommand;
import pl.longhorn.autopoly.district.DistrictDetailsCommand;
import pl.longhorn.autopoly.player.ClearPlayerCommand;
import pl.longhorn.autopoly.player.PlayerInBoardCommand;

@Component
@RequiredArgsConstructor
public class BoardListener {

    private final DistrictDetailsCommand districtDetailsCommand;
    private final PlayerInBoardCommand playerInBoardCommand;

    private final ClearDistrictDetailsCommand clearDistrictDetailsCommand;
    private final ClearPlayerCommand clearPlayerCommand;
    private final ClearBoardEventsCommand clearBoardEventsCommand;

    public void afterBoardCreate() {
        var district = districtDetailsCommand.prepareFields();
        playerInBoardCommand.create(district.getInitFieldId());
    }

    public void afterBoardDelete() {
        clearDistrictDetailsCommand.delete();
        clearPlayerCommand.clear();
        clearBoardEventsCommand.clear();
    }
}

package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.field.ClearDistrictDetailsCommand;
import pl.longhorn.autopoly.field.DistrictDetailsCommand;
import pl.longhorn.autopoly.player.ClearPlayerCommand;
import pl.longhorn.autopoly.player.PlayerInBoardCommand;

@Component
@RequiredArgsConstructor
class BoardListener {

    private final DistrictDetailsCommand districtDetailsCommand;
    private final PlayerInBoardCommand playerInBoardCommand;

    private final ClearDistrictDetailsCommand clearDistrictDetailsCommand;
    private final ClearPlayerCommand clearPlayerCommand;

    public void afterBoardCreate() {
        var district = districtDetailsCommand.prepareFields();
        playerInBoardCommand.create(district.getInitFieldId());
    }

    // TODO: use it!
    public void afterBoardDelete() {
        clearDistrictDetailsCommand.delete();
        clearPlayerCommand.clear();
    }
}

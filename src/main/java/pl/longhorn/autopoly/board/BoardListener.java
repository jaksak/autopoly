package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.field.ClearDistrictDetailsCommand;
import pl.longhorn.autopoly.field.DistrictDetailsCommand;
import pl.longhorn.autopoly.player.BotPlayerCommand;
import pl.longhorn.autopoly.player.ClearPlayerCommand;

@Component
@RequiredArgsConstructor
class BoardListener {

    private final DistrictDetailsCommand districtDetailsCommand;
    private final BotPlayerCommand botPlayerCommand;

    private final ClearDistrictDetailsCommand clearDistrictDetailsCommand;
    private final ClearPlayerCommand clearPlayerCommand;

    // TODO: use it!
    public void afterBoardCreate() {
        var district = districtDetailsCommand.prepareFields();
        for (int i = 0; i < 4; i++) {
            botPlayerCommand.create(district.getInitFieldId());
        }
    }

    // TODO: use it!
    public void afterBoardDelete() {
        clearDistrictDetailsCommand.delete();
        clearPlayerCommand.clear();
    }
}

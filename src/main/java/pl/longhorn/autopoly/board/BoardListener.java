package pl.longhorn.autopoly.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.field.ClearDistrictDetailsCommand;

@Component
@RequiredArgsConstructor
class BoardListener {

    private final ClearDistrictDetailsCommand clearDistrictDetailsCommand;

    // TODO: use it!
    public void afterBoardDelete() {
        clearDistrictDetailsCommand.delete();
    }
}

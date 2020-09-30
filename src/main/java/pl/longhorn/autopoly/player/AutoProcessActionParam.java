package pl.longhorn.autopoly.player;

import lombok.Builder;
import lombok.Getter;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.district.DistrictDetails;
import pl.longhorn.autopoly.randomizer.Randomizer;

@Getter
@Builder
public class AutoProcessActionParam {
    private final Board board;
    private final DistrictDetails districtDetails;
    private final Randomizer randomizer;
}

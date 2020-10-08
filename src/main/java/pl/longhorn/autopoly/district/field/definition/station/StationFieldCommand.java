package pl.longhorn.autopoly.district.field.definition.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.name.train.station.RandomTrainStationNameQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;
import pl.longhorn.autopoly.util.id.IdFactory;

@Service
@RequiredArgsConstructor
public class StationFieldCommand {

    private final IdFactory idFactory;
    private final RandomTrainStationNameQuery trainStationNameQuery;
    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    public StationField prepare() {
        return new StationField(idFactory.generate(), trainStationNameQuery.getRandom(), false, districtDetailsQuery, playerOwnershipQuery, fieldOwnershipQuery);
    }
}

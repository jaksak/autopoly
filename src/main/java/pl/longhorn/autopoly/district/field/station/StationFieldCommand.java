package pl.longhorn.autopoly.district.field.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.id.IdFactory;
import pl.longhorn.autopoly.name.train.station.RandomTrainStationNameQuery;

@Service
@RequiredArgsConstructor
public class StationFieldCommand {

    private final IdFactory idFactory;
    private final RandomTrainStationNameQuery trainStationNameQuery;

    public StationField prepare() {
        return new StationField(idFactory.generate(), trainStationNameQuery.getRandom());
    }
}

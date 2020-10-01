package pl.longhorn.autopoly.name.train.station;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomTrainStationNameQuery {

    private final TrainStationRepository repository;

    public String getRandom() {
        return repository.getRandom().getName();
    }
}

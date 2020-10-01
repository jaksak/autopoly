package pl.longhorn.autopoly.name.train.station;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface TrainStationRepository extends CrudRepository<TrainStationName, String> {

    @Query(nativeQuery = true, value = "SELECT *  FROM TRAIN_STATION_NAME ORDER BY random() LIMIT 1")
    TrainStationName getRandom();
}

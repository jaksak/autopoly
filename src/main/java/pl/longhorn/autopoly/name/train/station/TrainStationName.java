package pl.longhorn.autopoly.name.train.station;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
class TrainStationName {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String name;
}

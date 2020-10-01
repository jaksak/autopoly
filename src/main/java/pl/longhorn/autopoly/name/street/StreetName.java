package pl.longhorn.autopoly.name.street;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
class StreetName {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String name;
}

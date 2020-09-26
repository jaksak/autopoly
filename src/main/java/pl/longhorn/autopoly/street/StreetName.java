package pl.longhorn.autopoly.street;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class StreetName {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String name;
}

package pl.longhorn.autopoly.nick;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
class Nick {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String nickValue;
}

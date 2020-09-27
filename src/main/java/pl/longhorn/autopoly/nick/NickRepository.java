package pl.longhorn.autopoly.nick;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface NickRepository extends CrudRepository<Nick, String> {

    @Query(nativeQuery = true, value = "SELECT *  FROM NICK ORDER BY random() LIMIT 1")
    Nick getRandom();
}

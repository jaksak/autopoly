package pl.longhorn.autopoly.street.name;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface StreetNameRepository extends CrudRepository<StreetName, String> {

    @Query(nativeQuery = true, value = "SELECT *  FROM STREET_NAME ORDER BY random() LIMIT 1")
    StreetName getRandom();
}
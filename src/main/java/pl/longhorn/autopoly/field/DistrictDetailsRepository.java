package pl.longhorn.autopoly.field;

import org.springframework.stereotype.Repository;

@Repository
public class DistrictDetailsRepository {

    private final Object monitor = new Object();

    private DistrictDetails districtDetails;

    public void save(DistrictDetails districtDetails) {
        synchronized (monitor) {
            this.districtDetails = districtDetails;
        }
    }

    public DistrictDetails get() {
        return districtDetails;
    }
}

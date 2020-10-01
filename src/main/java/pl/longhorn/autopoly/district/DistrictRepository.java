package pl.longhorn.autopoly.district;

import org.springframework.stereotype.Repository;

@Repository
class DistrictRepository {

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

    public void clear() {
        synchronized (monitor) {
            districtDetails = null;
        }
    }
}

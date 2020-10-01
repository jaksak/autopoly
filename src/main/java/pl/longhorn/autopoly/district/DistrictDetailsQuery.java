package pl.longhorn.autopoly.district;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictDetailsQuery {

    private final DistrictRepository districtRepository;

    public DistrictDetails get() {
        return districtRepository.get();
    }
}

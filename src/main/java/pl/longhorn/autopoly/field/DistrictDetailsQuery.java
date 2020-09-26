package pl.longhorn.autopoly.field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictDetailsQuery {

    private final DistrictDetailsRepository districtDetailsRepository;

    public DistrictDetails get() {
        return districtDetailsRepository.get();
    }
}

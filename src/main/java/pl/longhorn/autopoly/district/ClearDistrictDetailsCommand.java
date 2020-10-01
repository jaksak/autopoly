package pl.longhorn.autopoly.district;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearDistrictDetailsCommand {

    private final DistrictRepository districtRepository;

    public void delete() {
        districtRepository.clear();
    }
}

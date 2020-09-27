package pl.longhorn.autopoly.field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearDistrictDetailsCommand {

    private final DistrictDetailsRepository districtDetailsRepository;

    public void delete() {
        districtDetailsRepository.clear();
    }
}

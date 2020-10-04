package pl.longhorn.autopoly.district;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Service
@RequiredArgsConstructor
// TODO: after splitting DistrictDetails - to field
public class FieldService {

    private final DistrictRepository districtRepository;

    public void update(AutopolyField field) {
        var districtDetails = districtRepository.get();
        districtDetails.update(field);
        districtRepository.save(districtDetails);
    }
}

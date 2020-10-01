package pl.longhorn.autopoly.district.field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;

@Service
@RequiredArgsConstructor
public class FieldQuery {

    private final DistrictDetailsQuery districtDetailsQuery;

    public AutopolyField getField(String fieldId) {
        return districtDetailsQuery.get().getFieldById(fieldId);
    }
}

package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.AutopolyField;

@Service
@RequiredArgsConstructor
public class FieldQuery {

    private final DistrictDetailsQuery districtDetailsQuery;

    public AutopolyField getField(String fieldId) {
        return districtDetailsQuery.get().getFieldById(fieldId);
    }
}

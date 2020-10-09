package pl.longhorn.autopoly.district.field.definition.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.policy.FieldPolicy;
import pl.longhorn.autopoly.district.field.policy.distriction.DistrictionFieldPolicy;
import pl.longhorn.autopoly.district.field.policy.house.HouseFieldPolicy;

@Component
@RequiredArgsConstructor
public class StreetFieldPolicy implements FieldPolicy<StreetField> {

    private final StreetDistrictionFieldPolicy districtionFieldPolicy = new StreetDistrictionFieldPolicy();
    private final StreetHouseFieldPolicy houseFieldPolicy = new StreetHouseFieldPolicy();

    @Override
    public Class<StreetField> getFieldClass() {
        return StreetField.class;
    }

    @Override
    public DistrictionFieldPolicy<StreetField> getDistrictionFieldPolicy() {
        return districtionFieldPolicy;
    }

    @Override
    public HouseFieldPolicy<StreetField> getHouseFieldPolicy() {
        return houseFieldPolicy;
    }
}

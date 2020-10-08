package pl.longhorn.autopoly.district.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.districted.DistrictedField;

import java.util.LinkedList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PlayerDistrict {
    private final String districtId;
    private final List<String> ownedFieldIds = new LinkedList<>();
    private final List<String> lackingFieldIds;

    public void addOwned(DistrictedField districtedField) {
        ownedFieldIds.add(districtedField.getId());
        lackingFieldIds.remove(districtedField.getId());
    }
}

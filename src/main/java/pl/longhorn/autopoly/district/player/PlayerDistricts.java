package pl.longhorn.autopoly.district.player;

import pl.longhorn.autopoly.district.field.districted.DistrictedField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerDistricts {
    private final Map<String, PlayerDistrict> districtsById = new HashMap<>();

    public void add(DistrictedField districtedField, List<String> fieldIdsByDistrictId) {
        var playerDistrict = districtsById.getOrDefault(districtedField.getDistrictId(), new PlayerDistrict(districtedField.getDistrictId(), fieldIdsByDistrictId));
        playerDistrict.addOwned(districtedField);
        districtsById.put(districtedField.getDistrictId(), playerDistrict);
    }

    public List<PlayerDistrict> getFull() {
        return districtsById.values().stream()
                .filter(district -> district.getLackingFieldIds().isEmpty())
                .collect(Collectors.toList());
    }
}

package pl.longhorn.autopoly.district.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerDistricts {
    private final Map<String, PlayerDistrict> districtsById = new HashMap<>();

    protected void addFieldWith(String fieldId, String districtId, List<String> fieldIdsWithTheSameDistrict) {
        var playerDistrict = districtsById.getOrDefault(districtId, new PlayerDistrict(districtId, fieldIdsWithTheSameDistrict));
        playerDistrict.addOwned(fieldId);
        districtsById.put(districtId, playerDistrict);
    }

    public List<PlayerDistrict> getFull() {
        return districtsById.values().stream()
                .filter(district -> district.getLackingFieldIds().isEmpty())
                .collect(Collectors.toList());
    }
}

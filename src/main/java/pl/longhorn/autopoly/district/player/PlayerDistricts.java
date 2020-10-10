package pl.longhorn.autopoly.district.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<PlayerDistrict> get(String districtId) {
        return Optional.ofNullable(districtsById.get(districtId));
    }

    public List<PlayerDistrict> getOneLacking() {
        return districtsById.values().stream()
                .filter(playerDistrict -> playerDistrict.getLackingFieldIds().size() == 1)
                .collect(Collectors.toList());
    }
}

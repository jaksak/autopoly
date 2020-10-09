package pl.longhorn.autopoly.district.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetails;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.cqrs.DistrictionFieldPolicyQuery;
import pl.longhorn.autopoly.player.PlayerOwnershipAccessor;

@Service
@RequiredArgsConstructor
public class PlayerDistrictCommand {

    private final PlayerOwnershipAccessor playerOwnershipAccessor;
    private final DistrictionFieldPolicyQuery districtionFieldPolicyQuery;
    private final DistrictDetailsQuery districtDetailsQuery;

    public PlayerDistricts prepare(String playerId) {
        PlayerDistricts playerDistricts = new PlayerDistricts();
        DistrictDetails districtDetails = districtDetailsQuery.get();
        for (var field : playerOwnershipAccessor.get(playerId)) {
            var districtPolicy = districtionFieldPolicyQuery.get(field);
            if (districtPolicy.hasAssignedDistrict()) {
                String districtId = districtPolicy.getDistrictId(field);
                playerDistricts.addFieldWith(field.getId(), districtId, districtDetails.getFieldIdsByDistrictId(districtId));
            }
        }
        return playerDistricts;
    }
}

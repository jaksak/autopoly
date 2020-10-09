package pl.longhorn.autopoly.district.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetails;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.cqrs.DistrictionFieldPolicyQuery;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

@Service
@RequiredArgsConstructor
public class PlayerDistrictCommand {

    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldQuery fieldQuery;
    private final DistrictionFieldPolicyQuery districtionFieldPolicyQuery;
    private final DistrictDetailsQuery districtDetailsQuery;

    public PlayerDistricts prepare(String playerId) {
        PlayerDistricts playerDistricts = new PlayerDistricts();
        DistrictDetails districtDetails = districtDetailsQuery.get();
        for (String fieldId : playerOwnershipQuery.get(playerId)) {
            AutopolyField field = fieldQuery.getField(fieldId);
            var districtPolicy = districtionFieldPolicyQuery.get(field);
            if (districtPolicy.hasAssignedDistrict()) {
                String districtId = districtPolicy.getDistrictId(field);
                playerDistricts.addFieldWith(fieldId, districtId, districtDetails.getFieldIdsByDistrictId(districtId));
            }
        }
        return playerDistricts;
    }
}

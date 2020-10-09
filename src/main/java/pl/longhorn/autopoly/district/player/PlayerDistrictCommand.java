package pl.longhorn.autopoly.district.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.DistrictDetails;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.district.field.districted.DistrictedField;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

@Service
@RequiredArgsConstructor
public class PlayerDistrictCommand {

    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldQuery fieldQuery;
    private final DistrictDetailsQuery districtDetailsQuery;

    public PlayerDistricts prepare(String playerId) {
        PlayerDistricts playerDistricts = new PlayerDistricts();
        DistrictDetails districtDetails = districtDetailsQuery.get();
        playerOwnershipQuery.get(playerId).stream()
                .map(fieldQuery::getField)
                .filter(field -> field instanceof DistrictedField)
                .map(field -> (DistrictedField) field)
                .forEach(field -> playerDistricts.add(field, districtDetails.getFieldIdsByDistrictId(field.getDistrictId())));
        return playerDistricts;
    }
}

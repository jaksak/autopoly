package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerOwnershipAccessor {

    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldQuery fieldQuery;

    public List<AutopolyField> get(String playerId) {
        return playerOwnershipQuery.get(playerId).stream()
                .map(fieldQuery::get)
                .collect(Collectors.toList());
    }
}

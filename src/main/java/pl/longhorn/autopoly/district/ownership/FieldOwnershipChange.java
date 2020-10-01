package pl.longhorn.autopoly.district.ownership;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldOwnershipChange {
    private final String playerId;
    private final String fieldId;
}

package pl.longhorn.autopoly.district;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldOwnerityChange {
    private final FieldOwnerityChangeType type;
    private final String fieldId;

    public FieldOwnerityChange add(String fieldId) {
        return new FieldOwnerityChange(FieldOwnerityChangeType.ADD, fieldId);
    }

    public FieldOwnerityChange remove(String fieldId) {
        return new FieldOwnerityChange(FieldOwnerityChangeType.REMOVE, fieldId);
    }
}

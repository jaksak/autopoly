package pl.longhorn.autopoly.district.unique;

import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.district.field.definition.street.StreetField;
import pl.longhorn.autopoly.name.street.RandomStreetNameQuery;

import java.util.List;

@RequiredArgsConstructor
public class UniqueStreetNameProvider {

    private final RandomStreetNameQuery randomStreetNameQuery;

    public String provide(List<StreetField> usedFields) {
        String name = getRandomName();
        for (int i = 0; i < 100 && isNotUnique(name, usedFields); i++) {
            name = getRandomName();
        }
        return name;
    }

    private boolean isNotUnique(String name, List<StreetField> usedFields) {
        return usedFields
                .stream()
                .anyMatch(streetField -> streetField.getName().equals(name));
    }

    private String getRandomName() {
        return randomStreetNameQuery.getRandom();
    }
}

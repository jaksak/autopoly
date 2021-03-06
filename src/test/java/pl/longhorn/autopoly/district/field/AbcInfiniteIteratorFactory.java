package pl.longhorn.autopoly.district.field;

import java.util.List;
import java.util.Map;

public class AbcInfiniteIteratorFactory {

    public static FieldInfiniteIterator prepareFieldInfiniteIterator() {
        var fieldIds = AbcInfiniteIteratorFactory.prepareFieldIds();
        var playerById = AbcInfiniteIteratorFactory.preparePlayerById();

        return new FieldInfiniteIterator(fieldIds, playerById);
    }

    public static Map<String, AutopolyField> preparePlayerById() {
        return Map.of(
                "A", prepareFakeField("A"),
                "B", prepareFakeField("B"),
                "C", prepareFakeField("C")
        );
    }

    public static List<String> prepareFieldIds() {
        return List.of("A", "B", "C");
    }

    private static AutopolyField prepareFakeField(String id) {
        return new AutopolyField() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public AutopolyField reset() {
                return null;
            }
        };
    }
}

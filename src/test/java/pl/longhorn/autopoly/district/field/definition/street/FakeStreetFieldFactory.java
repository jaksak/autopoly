package pl.longhorn.autopoly.district.field.definition.street;

public class FakeStreetFieldFactory {

    public static StreetField getStreetField(String fieldId, String districtId) {
        return new StreetField(fieldId, districtId, fieldId, 5, 10, 15, false, null, null, null);
    }
}

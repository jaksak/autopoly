package pl.longhorn.autopoly.district;

import lombok.Getter;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.FieldInfiniteIterator;
import pl.longhorn.autopoly.district.field.definition.station.StationField;
import pl.longhorn.autopoly.district.field.definition.street.StreetField;

import java.util.*;
import java.util.stream.Collectors;

// TODO: separate card & district?
public class DistrictDetails {
    private final Map<String, AutopolyField> fieldById = new HashMap<>();

    private final List<String> fieldIdByBoardOrder = new ArrayList<>();
    private final Map<String, List<String>> fieldIdsByDistrictId = new HashMap<>();
    @Getter
    private final String initFieldId;

    public void addNoDistricted(AutopolyField field) {
        String fieldId = field.getId();
        fieldById.put(fieldId, field);
        fieldIdByBoardOrder.add(fieldId);
    }

    public void addDistricted(AutopolyField districtedField, String districtId) {
        addNoDistricted(districtedField);
        var fieldsInTheSameDistrict = getFieldsInDistrict(districtId);
        fieldsInTheSameDistrict.add(districtedField.getId());
        fieldIdsByDistrictId.put(districtId, fieldsInTheSameDistrict);
    }

    private List<String> getFieldsInDistrict(String districtId) {
        return fieldIdsByDistrictId.computeIfAbsent(districtId, (id) -> new ArrayList<>());
    }

    public List<AutopolyField> getFieldByBoardOrder() {
        return fieldIdByBoardOrder.stream().map(fieldById::get).collect(Collectors.toList());
    }

    public FieldInfiniteIterator getFieldIterator() {
        return new FieldInfiniteIterator(fieldIdByBoardOrder, fieldById);
    }

    public AutopolyField getFieldById(String fieldId) {
        return fieldById.get(fieldId);
    }

    public DistrictDetails(AutopolyField initField) {
        addNoDistricted(initField);
        this.initFieldId = initField.getId();
    }

    public List<StreetField> getStreetFields() {
        List<StreetField> fields = new LinkedList<>();
        fieldById.values().forEach(field -> {
            if (field instanceof StreetField) {
                fields.add((StreetField) field);
            }
        });
        return fields;
    }

    protected void update(AutopolyField field) {
        fieldById.computeIfPresent(field.getId(), (id, currentCard) -> field);
    }

    public List<String> getFieldIdsByDistrictId(String districtId) {
        return new LinkedList<>(fieldIdsByDistrictId.get(districtId));
    }

    public List<String> getStationFieldIds() {
        return fieldById.values().stream()
                .filter(field -> field instanceof StationField)
                .map(AutopolyField::getId)
                .collect(Collectors.toList());
    }
}

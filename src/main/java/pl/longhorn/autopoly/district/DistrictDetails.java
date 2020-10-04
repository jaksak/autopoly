package pl.longhorn.autopoly.district;

import lombok.Getter;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.FieldInfiniteIterator;
import pl.longhorn.autopoly.district.field.definition.street.StreetField;
import pl.longhorn.autopoly.district.field.districted.DistrictedField;

import java.util.*;
import java.util.stream.Collectors;

// TODO: separate card & district?
public class DistrictDetails {
    private final Map<String, AutopolyField> fieldById = new HashMap<>();

    private final List<String> fieldIdByBoardOrder = new ArrayList<>();
    private final Map<String, List<String>> fieldIdsByDistrictId = new HashMap<>();
    @Getter
    private final String initFieldId;

    public void add(AutopolyField field) {
        if (field instanceof DistrictedField) {
            DistrictedField districtedField = (DistrictedField) field;
            addDistricted(districtedField);
        } else {
            addNoDistricted(field);
        }
    }

    private void addNoDistricted(AutopolyField field) {
        String fieldId = field.getId();
        fieldById.put(fieldId, field);
        fieldIdByBoardOrder.add(fieldId);
    }

    private void addDistricted(DistrictedField districtedField) {
        addNoDistricted(districtedField);
        var fieldsInTheSameDistrict = getFieldsInDistrict(districtedField.getDistrictId());
        fieldsInTheSameDistrict.add(districtedField.getId());
        fieldIdsByDistrictId.put(districtedField.getDistrictId(), fieldsInTheSameDistrict);
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
}

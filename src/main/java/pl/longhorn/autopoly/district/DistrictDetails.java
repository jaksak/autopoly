package pl.longhorn.autopoly.district;

import lombok.Getter;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.FieldInfiniteIterator;

import java.util.*;
import java.util.stream.Collectors;

public class DistrictDetails {
    private final Map<String, AutopolyField> fieldById = new HashMap<>();

    private final List<String> fieldIdByBoardOrder = new ArrayList<>();
    private final Map<String, List<String>> fieldIdsByDistrictId = new HashMap<>();
    @Getter
    private final String initFieldId;

    protected void addNoDistricted(AutopolyField field) {
        String fieldId = field.getId();
        fieldById.put(fieldId, field);
        fieldIdByBoardOrder.add(fieldId);
    }

    protected void addDistricted(AutopolyField field, String districtId) {
        addNoDistricted(field);
        var fieldsInTheSameDistrict = getFieldsInDistrict(districtId);
        fieldsInTheSameDistrict.add(field.getId());
        fieldIdsByDistrictId.put(districtId, fieldsInTheSameDistrict);
    }

    private List<String> getFieldsInDistrict(String districtId) {
        return fieldIdsByDistrictId.computeIfAbsent(districtId, (id) -> new ArrayList<>());
    }

    public List<AutopolyField> getFieldByBoardOrder() {
        return fieldIdByBoardOrder.stream().map(fieldById::get).collect(Collectors.toList());
    }

    public Iterator<AutopolyField> getFieldIterator() {
        return new FieldInfiniteIterator(fieldIdByBoardOrder, fieldById);
    }

    public DistrictDetails(AutopolyField initField) {
        addNoDistricted(initField);
        this.initFieldId = initField.getId();
    }
}
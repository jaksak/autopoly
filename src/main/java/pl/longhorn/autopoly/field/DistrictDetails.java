package pl.longhorn.autopoly.field;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public DistrictDetails(AutopolyField initField) {
        addNoDistricted(initField);
        this.initFieldId = initField.getId();
    }
}

package pl.longhorn.autopoly.district.field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.district.FieldService;
import pl.longhorn.autopoly.district.field.housable.HousableField;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.FieldUpdateLogContent;
import pl.longhorn.autopoly.player.UpdateMoneyCommand;

@Service
@RequiredArgsConstructor
public class HouseLvlCommand {

    private final FieldService fieldService;
    private final FieldQuery fieldQuery;
    private final UpdateMoneyCommand updateMoneyCommand;
    private final BoardQuery boardQuery;
    private final BoardLogCommand boardLogCommand;

    public void increaseLvl(String fieldId, String founder) {
        var field = fieldQuery.getField(fieldId);
        if (field instanceof HousableField) {
            var housableField = (HousableField) field;
            if (housableField.shouldIncreaseHouseLvl()) {
                var changedField = housableField.increaseHouseLvl();
                fieldService.update(changedField);
                updateMoneyCommand.updateMoney(founder, -housableField.getCurrentHousePrice());
                boardLogCommand.add(new FieldUpdateLogContent(changedField.toView()), boardQuery.get().getId());
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}

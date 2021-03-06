package pl.longhorn.autopoly.district.field.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.district.FieldService;
import pl.longhorn.autopoly.district.field.policy.FieldPolicyFactory;
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
    private final FieldPolicyFactory fieldPolicyFactory;
    private final FieldViewQuery fieldViewQuery;

    public void increaseLvl(String fieldId, String founderId) {
        var field = fieldQuery.get(fieldId);
        var housePolicy = fieldPolicyFactory.getPolicy(field).getHouseFieldPolicy();
        if (housePolicy.shouldIncreaseHouseLvl(field)) {
            int price = housePolicy.getCurrentHousePrice(field);
            var changedField = housePolicy.increaseHouseLvl(field);
            fieldService.update(changedField);
            updateMoneyCommand.updateMoney(founderId, -price);
            boardLogCommand.add(new FieldUpdateLogContent(fieldViewQuery.get(changedField)), boardQuery.get().getId());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void decreaseHouseLvl(String fieldId, String ownerId) {
        var field = fieldQuery.get(fieldId);
        var housePolicy = fieldPolicyFactory.getPolicy(field).getHouseFieldPolicy();
        if (housePolicy.shouldDecreaseHouseLvl(field)) {
            var afterUpdateField = housePolicy.decreaseHouseLvl(field);
            fieldService.update(afterUpdateField);
            updateMoneyCommand.updateMoney(ownerId, housePolicy.getCurrentHousePrice(afterUpdateField));
            boardLogCommand.add(new FieldUpdateLogContent(fieldViewQuery.get(field)), boardQuery.get().getId());
        } else {
            throw new IllegalArgumentException();
        }
    }
}

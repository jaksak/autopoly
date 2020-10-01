package pl.longhorn.autopoly.player.state;

import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.BoardActionResult;
import pl.longhorn.autopoly.board.BoardQuery;
import pl.longhorn.autopoly.board.event.walk.WalkBoardEventFactory;
import pl.longhorn.autopoly.dice.DiceCommand;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.DistrictGoToCalculator;
import pl.longhorn.autopoly.district.ownership.MoneyChange;
import pl.longhorn.autopoly.log.content.PlayerWalkLogContent;
import pl.longhorn.autopoly.player.Player;

@RequiredArgsConstructor
public class WalkPlayerState implements PlayerState {

    private static final int MONEY_CHANGE_AFTER_START = 50;
    private final DiceCommand diceCommand;
    private final BoardQuery boardQuery;
    private final WalkBoardEventFactory walkBoardEventFactory;
    private final DistrictDetailsQuery districtDetailsQuery;

    @Override
    public BoardActionResult autoProcessAction(Player player) {
        var board = boardQuery.get();
        var diceResult = diceCommand.rollWithCombo(board.getId(), player.getId());
        if (diceResult.shouldPunish()) {
            // TODO: punish player!
            return BoardActionResult.builder()
                    .build();
        } else {
            var district = districtDetailsQuery.get();
            var calculator = new DistrictGoToCalculator(district.getFieldIterator());
            var newField = calculator.getFieldAfter(player.getCurrentFieldId(), diceResult.sum());
            var resultBuilder = BoardActionResult.builder()
                    .event(walkBoardEventFactory.create(player.getId(), newField.getId()))
                    .log(new PlayerWalkLogContent(player.getId(), newField.getId()));
            if (calculator.isRecountFromZero()) {
                resultBuilder.moneyChange(new MoneyChange(player.getId(), MONEY_CHANGE_AFTER_START));
            }
            return resultBuilder.build();
        }
    }
}

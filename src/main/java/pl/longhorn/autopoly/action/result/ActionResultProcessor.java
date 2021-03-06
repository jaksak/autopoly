package pl.longhorn.autopoly.action.result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.board.event.cqrs.AddBoardEventsCommand;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.player.UpdateMoneyCommand;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipUpdateCommand;

@Service
@RequiredArgsConstructor
public class ActionResultProcessor {

    private final FieldOwnershipUpdateCommand fieldOwnershipUpdateCommand;
    private final BoardLogCommand boardLogCommand;
    private final AddBoardEventsCommand addBoardEventsCommand;
    private final UpdateMoneyCommand updateMoneyCommand;
    private final BoardQuery boardQuery;

    public void processResult(BoardActionResult actionResult) {
        var board = boardQuery.get();
        actionResult.getLogs().forEach(log -> boardLogCommand.add(log, board.getId()));
        if (!actionResult.getEvents().isEmpty()) {
            addBoardEventsCommand.add(actionResult.getEvents());
        }
        if (!actionResult.getFieldOwnershipChanges().isEmpty()) {
            actionResult.getFieldOwnershipChanges().forEach(fieldOwnershipChange -> fieldOwnershipUpdateCommand.changeOwnership(fieldOwnershipChange.getPlayerId(), fieldOwnershipChange.getFieldId()));
        }
        if (!actionResult.getMoneyChanges().isEmpty()) {
            actionResult.getMoneyChanges().forEach(moneyChange -> updateMoneyCommand.updateMoney(moneyChange.getPlayerId(), moneyChange.getMoneyAmount()));
        }
    }
}

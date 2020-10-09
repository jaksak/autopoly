package pl.longhorn.autopoly.board.event.definition.walk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.cqrs.ActionFieldPolicyQuery;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.log.content.PlayerWalkLogContent;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.UpdatePlayerPositionCommand;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class WalkBoardEvent implements BoardEvent {
    @Getter
    private final String id;
    @Getter
    private final String playerId;
    private final String fieldId;

    private final FieldQuery fieldQuery;
    private final UpdatePlayerPositionCommand updatePlayerPositionCommand;
    private final FieldOwnershipQuery fieldOwnershipQuery;
    private final ActionFieldPolicyQuery actionFieldPolicyQuery;

    @Override
    public BoardActionResult react(Board board, Player player) {
        updatePlayerPositionCommand.update(playerId, fieldId);
        var field = fieldQuery.get(fieldId);
        return actionFieldPolicyQuery.get(field)
                .countActionAfterPlayerStay(prepareParam(player, field)).toBuilder()
                .log(new PlayerWalkLogContent(playerId, fieldId))
                .build();
    }

    private <T extends AutopolyField> AutopolyFieldActionParam<T> prepareParam(Player player, T field) {
        return AutopolyFieldActionParam.<T>builder()
                .ownerId(fieldOwnershipQuery.getOwner(fieldId).orElse(null))
                .player(player)
                .field(field)
                .build();
    }
}

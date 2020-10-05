package pl.longhorn.autopoly.board.event.walk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.district.field.AutopolyFieldActionParam;
import pl.longhorn.autopoly.district.field.FieldQuery;
import pl.longhorn.autopoly.log.content.PlayerWalkLogContent;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.player.PlayerInBoardQuery;
import pl.longhorn.autopoly.player.UpdatePlayerPositionCommand;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class WalkBoardEvent implements BoardEvent {
    @Getter
    private final String id;
    @Getter
    private final String playerId;
    private final String fieldId;

    private final FieldQuery fieldQuery;
    private final UpdatePlayerPositionCommand updatePlayerPositionCommand;
    private final PlayerInBoardQuery playerInBoardQuery;

    @Override
    public BoardActionResult react(Board board, Player player) {
        updatePlayerPositionCommand.update(playerId, fieldId);
        var field = fieldQuery.getField(fieldId);
        return field.afterPlayerStay(prepareParam(player)).toBuilder()
                .log(new PlayerWalkLogContent(playerId, fieldId))
                .build();
    }

    private AutopolyFieldActionParam prepareParam(Player player) {
        return AutopolyFieldActionParam.builder()
                .ownerId(getOwnerId())
                .player(player)
                .build();
    }

    private String getOwnerId() {
        return playerInBoardQuery.get().getPlayers().stream()
                .filter(player -> player.getOwnedFieldIds().contains(fieldId))
                .findAny()
                .map(Player::getId)
                .orElse(null);
    }
}

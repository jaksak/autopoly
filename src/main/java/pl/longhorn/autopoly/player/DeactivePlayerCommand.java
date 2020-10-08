package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.district.field.ResetFieldCommand;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.DeactivatePlayerLogContent;
import pl.longhorn.autopoly.player.ownership.cqrs.ClearPlayerOwnershipCommand;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeactivePlayerCommand {

    private final ResetFieldCommand resetFieldCommand;
    private final PlayerInBoardQuery playerInBoardQuery;
    private final PlayerRepository playerRepository;
    private final BoardLogCommand boardLogCommand;
    private final BoardQuery boardQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final ClearPlayerOwnershipCommand clearPlayerOwnershipCommand;

    public void deactivate(String playerId) {
        var playersInBoard = playerInBoardQuery.get();
        var player = playersInBoard.getPlayerById(playerId);
        resetAllCard(playerOwnershipQuery.get(playerId));
        player.setActive(false);
        player.setCurrentFieldId(null);
        clearPlayerOwnershipCommand.clear(playerId);
        playerRepository.save(playersInBoard);
        inform(playerId);
    }

    private void resetAllCard(List<String> fieldIds) {
        fieldIds.forEach(resetFieldCommand::reset);
    }

    public void inform(String playerId) {
        boardLogCommand.add(new DeactivatePlayerLogContent(playerId), boardQuery.get().getId());
    }
}

package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.BoardQuery;
import pl.longhorn.autopoly.district.field.ResetFieldCommand;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.DeactivatePlayerLogContent;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeactivePlayerCommand {

    private final ResetFieldCommand resetFieldCommand;
    private final PlayerInBoardQuery playerInBoardQuery;
    private final PlayerRepository playerRepository;
    private final BoardLogCommand boardLogCommand;
    private final BoardQuery boardQuery;

    public void deactivate(String playerId) {
        var playersInBoard = playerInBoardQuery.get();
        var player = playersInBoard.getPlayerById(playerId);
        resetAllCard(player.getOwnedFieldIds());
        player.setActive(false);
        player.setCurrentFieldId(null);
        player.setOwnedFieldIds(List.of());
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

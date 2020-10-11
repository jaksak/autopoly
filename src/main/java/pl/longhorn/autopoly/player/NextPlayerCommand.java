package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.log.BoardLogCommand;
import pl.longhorn.autopoly.log.content.NextTurnLogContent;
import pl.longhorn.autopoly.player.next.NextPlayerCalculator;

@Service
@RequiredArgsConstructor
public class NextPlayerCommand {

    private final PlayerRepository repository;
    private final BoardLogCommand boardLogCommand;
    private final BoardQuery boardQuery;

    public void moveActionToNext() {
        var playerInBoard = repository.get();
            var nextPlayerId = new NextPlayerCalculator().getNext(playerInBoard.getNextPlayerId(), playerInBoard.getPlayers());
            playerInBoard.setNextPlayerId(nextPlayerId);
            repository.save(playerInBoard);
            boardLogCommand.add(new NextTurnLogContent(nextPlayerId), boardQuery.get().getId());
    }
}

package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.player.next.NextPlayerCalculator;

@Service
@RequiredArgsConstructor
public class NextPlayerCommand {

    private final PlayerRepository repository;

    public void moveActionToNext() {
        var playerInBoard = repository.get();
        playerInBoard.setNextPlayerId(new NextPlayerCalculator().getNext(playerInBoard.getNextPlayerId(), playerInBoard.getPlayers()));
        repository.save(playerInBoard);
    }

    public void setNext(String nextPlayerId) {
        var playerInBoard = repository.get();
        playerInBoard.setNextPlayerId(nextPlayerId);
        repository.save(playerInBoard);
    }
}

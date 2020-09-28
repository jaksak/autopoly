package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.player.next.NextPlayerCalculator;

@Service
@RequiredArgsConstructor
public class CalculateNextPlayerCommand {

    private final PlayerRepository playerRepository;

    public String next() {
        var playerInBoard = playerRepository.get();
        var nextPlayer = new NextPlayerCalculator().getNext(playerInBoard.getNextPlayerId(), playerInBoard.getPlayers());
        playerInBoard.setNextPlayerId(nextPlayer);
        playerRepository.save(playerInBoard);
        return nextPlayer;
    }
}

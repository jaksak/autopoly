package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NextPlayerQuery {

    private final PlayerRepository playerRepository;

    public Player get() {
        var playerInBoard = playerRepository.get();
        return playerInBoard.getPlayers().stream()
                .filter(player -> player.getId().equals(playerInBoard.getNextPlayerId()))
                .findAny()
                .orElseThrow();
    }
}

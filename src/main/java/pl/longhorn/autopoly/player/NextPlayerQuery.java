package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NextPlayerQuery {

    private final PlayerRepository playerRepository;

    public Optional<Player> get() {
        var playerInBoard = playerRepository.get();
        if (playerInBoard == null || playerInBoard.getNextPlayerId() == null) {
            return Optional.empty();
        } else {
            var nextPlayerId = playerInBoard.getNextPlayerId();
            return playerInBoard.getPlayers().stream()
                    .filter(player -> player.getId().equals(nextPlayerId))
                    .findAny();
        }
    }
}

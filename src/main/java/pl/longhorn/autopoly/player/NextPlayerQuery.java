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
        var nextPlayerId = playerInBoard.getNextPlayerId();
        if (nextPlayerId == null) {
            return Optional.empty();
        } else {
            return playerInBoard.getPlayers().stream()
                    .filter(player -> player.getId().equals(nextPlayerId))
                    .findAny();
        }
    }
}

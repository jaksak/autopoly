package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayersQuery {

    private final PlayerRepository playerRepository;

    public List<Player> get() {
        var playerInBoard = playerRepository.get();
        if (playerInBoard == null) {
            return List.of();
        } else {
            return playerInBoard.getPlayers();
        }
    }
}

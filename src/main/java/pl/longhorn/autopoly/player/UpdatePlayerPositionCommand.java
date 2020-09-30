package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePlayerPositionCommand {

    private final PlayerRepository playerRepository;

    public void update(String playerId, String fieldId) {
        var players = playerRepository.get();
        players.getPlayerById(playerId).setCurrentFieldId(fieldId);
        playerRepository.save(players);
    }
}

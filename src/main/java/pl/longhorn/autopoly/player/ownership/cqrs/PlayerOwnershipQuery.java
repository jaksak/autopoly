package pl.longhorn.autopoly.player.ownership.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.player.ownership.PlayerOwnershipRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerOwnershipQuery {

    private final PlayerOwnershipRepository playerOwnershipRepository;

    public List<String> get(String playerId) {
        return playerOwnershipRepository.getPlayerFields(playerId);
    }
}

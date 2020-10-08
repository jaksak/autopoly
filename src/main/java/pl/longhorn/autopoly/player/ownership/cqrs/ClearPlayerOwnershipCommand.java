package pl.longhorn.autopoly.player.ownership.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.player.ownership.PlayerOwnershipRepository;

@Service
@RequiredArgsConstructor
public class ClearPlayerOwnershipCommand {

    private final PlayerOwnershipRepository playerOwnershipRepository;

    public void clear(String playerId) {
        playerOwnershipRepository.clearPlayerOwnership(playerId);
    }
}

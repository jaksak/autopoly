package pl.longhorn.autopoly.player.ownership.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.player.ownership.PlayerOwnershipRepository;

@Service
@RequiredArgsConstructor
public class FieldOwnershipUpdateCommand {

    private final PlayerOwnershipRepository playerOwnershipRepository;

    public void changeOwnership(String playerId, String fieldId) {
        playerOwnershipRepository.removeFieldOwnership(fieldId);
        playerOwnershipRepository.addFieldToPlayer(fieldId, playerId);
    }
}

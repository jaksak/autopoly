package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldOwnershipUpdateCommand {

    private final PlayerRepository playerRepository;

    public void changeOwnership(String playerId, String fieldId) {
        var players = playerRepository.get();
        changeOwnershipInternal(fieldId, players, playerId);
        players.getPlayerById(playerId).getOwnedFieldIds().add(fieldId);
        playerRepository.save(players);
    }

    private void changeOwnershipInternal(String fieldId, PlayerInBoard players, String playerId) {
        players.getPlayers().replaceAll(player -> {
            if (player.getId().equals(playerId)) {
                return addOwnershipToPlayer(fieldId, player);
            } else {
                return removeOwnership(fieldId, player);
            }
        });
    }

    private Player removeOwnership(String fieldId, Player player) {
        List<String> fieldIds = new LinkedList<>(player.getOwnedFieldIds());
        fieldIds.remove(fieldId);
        player.setOwnedFieldIds(fieldIds);
        return player;
    }

    private Player addOwnershipToPlayer(String fieldId, Player player) {
        List<String> fieldIds = new LinkedList<>(player.getOwnedFieldIds());
        fieldIds.add(fieldId);
        player.setOwnedFieldIds(fieldIds);
        return player;
    }
}

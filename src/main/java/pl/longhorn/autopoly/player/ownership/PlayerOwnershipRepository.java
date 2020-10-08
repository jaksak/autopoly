package pl.longhorn.autopoly.player.ownership;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlayerOwnershipRepository {

    private final Object monitor = new Object();
    private final Map<String, Set<String>> ownedFieldIdsByPlayerId = new HashMap<>();
    private final Map<String, String> playerIdByFieldId = new HashMap<>();

    public List<String> getPlayerFields(String playerId) {
        var fieldIds = ownedFieldIdsByPlayerId.get(playerId);
        return fieldIds == null ? new LinkedList<>() : List.copyOf(fieldIds);
    }

    public String getOwnerId(String fieldId) {
        return playerIdByFieldId.get(fieldId);
    }

    public void addFieldToPlayer(String fieldId, String playerId) {
        synchronized (monitor) {
            var fieldIds = ownedFieldIdsByPlayerId.getOrDefault(playerId, new HashSet<>());
            fieldIds.add(fieldId);
            ownedFieldIdsByPlayerId.put(playerId, fieldIds);
            playerIdByFieldId.put(fieldId, playerId);
        }
    }

    public void removeFieldOwnership(String fieldId) {
        synchronized (monitor) {
            var playerId = playerIdByFieldId.get(fieldId);
            if (playerId != null) {
                var fieldIds = ownedFieldIdsByPlayerId.getOrDefault(playerId, new HashSet<>());
                fieldIds.remove(fieldId);
                ownedFieldIdsByPlayerId.put(playerId, fieldIds);
                playerIdByFieldId.remove(fieldId);
            }
        }
    }

    public void clear() {
        synchronized (monitor) {
            ownedFieldIdsByPlayerId.clear();
            playerIdByFieldId.clear();
        }
    }

    public void clearPlayerOwnership(String playerId) {
        synchronized (monitor) {
            getPlayerFields(playerId).forEach(playerIdByFieldId::remove);
            ownedFieldIdsByPlayerId.remove(playerId);
        }
    }
}

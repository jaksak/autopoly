package pl.longhorn.autopoly.player.ownership.cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.player.ownership.PlayerOwnershipRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldOwnershipQuery {

    private final PlayerOwnershipRepository playerOwnershipRepository;

    public Optional<String> getOwner(String fieldId) {
        return Optional.ofNullable(playerOwnershipRepository.getOwnerId(fieldId));
    }
}

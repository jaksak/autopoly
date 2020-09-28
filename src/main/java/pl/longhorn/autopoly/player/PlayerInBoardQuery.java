package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerInBoardQuery {

    private final PlayerRepository playerRepository;

    public PlayerInBoard get() {
        return playerRepository.get();
    }
}

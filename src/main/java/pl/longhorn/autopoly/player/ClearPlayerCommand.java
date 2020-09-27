package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearPlayerCommand {

    private final PlayerRepository playerRepository;

    public void clear() {
        playerRepository.clear();
    }
}

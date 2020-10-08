package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PlayerInBoardCommand {

    private final PlayerRepository playerRepository;
    private final BotPlayerFactory botPlayerFactory;
    private final Randomizer randomizer;

    public void create(String startFieldId) {
        var players = preparePlayers(startFieldId);
        var playerInBoard = new PlayerInBoard(players, players.get(0).getId());
        playerRepository.save(playerInBoard);
    }

    private List<Player> preparePlayers(String startFieldId) {
        return IntStream.range(0, getPlayerAmount())
                .mapToObj(i -> botPlayerFactory.create(startFieldId))
                .collect(Collectors.toList());
    }

    private int getPlayerAmount() {
        return randomizer.nextInt(4, 6);
    }
}

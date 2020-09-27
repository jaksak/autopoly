package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.id.IdFactory;
import pl.longhorn.autopoly.nick.NickQuery;
import pl.longhorn.autopoly.player.type.BotPlayerType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BotPlayerCommand {

    private final IdFactory idFactory;
    private final BotPlayerType botPlayerType = new BotPlayerType();
    private final PlayerRepository repository;
    private final NickQuery nickQuery;

    public Player create(String startFieldId) {
        var player = new Player(idFactory.generate(), nickQuery.getRandom(), 0, startFieldId, List.of(), botPlayerType);
        repository.save(player);
        return player;
    }
}
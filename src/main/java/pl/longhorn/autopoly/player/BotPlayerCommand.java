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

    private static final int INIT_MONEY_AMOUNT = 200;
    private final IdFactory idFactory;
    private final BotPlayerType botPlayerType = new BotPlayerType();
    private final PlayerRepository repository;
    private final NickQuery nickQuery;

    public Player create(String startFieldId) {
        var player = new Player(idFactory.generate(), nickQuery.getRandom(), INIT_MONEY_AMOUNT, startFieldId, List.of(), botPlayerType);
        repository.save(player);
        return player;
    }
}
package pl.longhorn.autopoly.player;

import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.board.cqrs.BoardQuery;
import pl.longhorn.autopoly.board.event.definition.walk.WalkBoardEventFactory;
import pl.longhorn.autopoly.dice.DiceCommand;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.nick.NickQuery;
import pl.longhorn.autopoly.player.state.WalkPlayerState;
import pl.longhorn.autopoly.player.type.BotPlayerType;
import pl.longhorn.autopoly.util.id.IdFactory;

@Service
class BotPlayerFactory {

    private static final int INIT_MONEY_AMOUNT = 500;
    private final IdFactory idFactory;
    private final BotPlayerType botPlayerType = new BotPlayerType();
    private final NickQuery nickQuery;
    private final WalkPlayerState walkPlayerState;

    public Player create(String startFieldId) {
        return new Player(idFactory.generate(), nickQuery.getRandom(), INIT_MONEY_AMOUNT, startFieldId, botPlayerType, walkPlayerState, true);
    }

    BotPlayerFactory(IdFactory idFactory, DiceCommand diceCommand, NickQuery nickQuery, BoardQuery boardQuery, WalkBoardEventFactory walkBoardEventFactory, DistrictDetailsQuery districtDetailsQuery) {
        this.idFactory = idFactory;
        this.nickQuery = nickQuery;
        this.walkPlayerState = new WalkPlayerState(diceCommand, boardQuery, walkBoardEventFactory, districtDetailsQuery);
    }
}
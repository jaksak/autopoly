package pl.longhorn.autopoly.board.event.definition.trade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.autopoly.district.field.cqrs.DistrictionFieldPolicyQuery;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.district.player.PlayerDistrictCommand;
import pl.longhorn.autopoly.trade.TradeProposition;
import pl.longhorn.autopoly.trade.cqrs.ClearTradePropositionCommand;
import pl.longhorn.autopoly.trade.cqrs.RunTradePropositionCommand;
import pl.longhorn.autopoly.util.id.IdFactory;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

@Component
@RequiredArgsConstructor
public class TradeBoardEventFactory {

    private final IdFactory idFactory;
    private final Randomizer randomizer;
    private final PlayerDistrictCommand playerDistrictCommand;
    private final FieldQuery fieldQuery;
    private final DistrictionFieldPolicyQuery districtionFieldPolicyQuery;
    private final RunTradePropositionCommand runTradePropositionCommand;
    private final ClearTradePropositionCommand clearTradePropositionCommand;

    public TradeBoardEvent create(String playerId, TradeProposition tradeProposition) {
        return new TradeBoardEvent(idFactory.generate(), playerId, tradeProposition, randomizer, playerDistrictCommand, fieldQuery,
                districtionFieldPolicyQuery, runTradePropositionCommand, clearTradePropositionCommand);
    }
}

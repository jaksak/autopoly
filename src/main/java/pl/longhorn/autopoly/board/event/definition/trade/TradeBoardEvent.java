package pl.longhorn.autopoly.board.event.definition.trade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.action.result.BoardActionResult;
import pl.longhorn.autopoly.board.Board;
import pl.longhorn.autopoly.board.event.BoardEvent;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.cqrs.DistrictionFieldPolicyQuery;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.district.player.PlayerDistrictCommand;
import pl.longhorn.autopoly.district.player.PlayerDistricts;
import pl.longhorn.autopoly.player.Player;
import pl.longhorn.autopoly.trade.TradeProposition;
import pl.longhorn.autopoly.trade.cqrs.ClearTradePropositionCommand;
import pl.longhorn.autopoly.trade.cqrs.RunTradePropositionCommand;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

@Getter
@RequiredArgsConstructor
public class TradeBoardEvent implements BoardEvent {
    private final String id;
    private final String playerId;
    private final TradeProposition tradeProposition;

    private final Randomizer randomizer;
    private final PlayerDistrictCommand playerDistrictCommand;
    private final FieldQuery fieldQuery;
    private final DistrictionFieldPolicyQuery districtionFieldPolicyQuery;
    private final RunTradePropositionCommand runTradePropositionCommand;
    private final ClearTradePropositionCommand clearTradePropositionCommand;

    @Override
    public BoardActionResult react(Board board, Player player) {
        if (shouldAccept(player)) {
            runTradePropositionCommand.run();
        } else {
            clearTradePropositionCommand.run();
        }
        return BoardActionResult.builder()
                .build();
    }

    private boolean shouldAccept(Player player) {
        var playerDistricts = playerDistrictCommand.prepare(player.getId());
        var offer = tradeProposition.getOffer1();
        for (String fieldId : offer.getFields()) {
            var field = fieldQuery.get(fieldId);
            var districtionPolicy = districtionFieldPolicyQuery.get(field);
            if (districtionPolicy.hasAssignedDistrict() && isCreateDistrict(field, districtionPolicy.getDistrictId(field), playerDistricts)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCreateDistrict(AutopolyField field, String districtId, PlayerDistricts playerDistricts) {
        var optionalPlayerDistrict = playerDistricts.get(districtId);
        if (optionalPlayerDistrict.isPresent()) {
            var lackingFields = optionalPlayerDistrict.get().getLackingFieldIds();
            return lackingFields.size() == 1 && lackingFields.contains(field.getId());
        } else {
            return false;
        }
    }
}

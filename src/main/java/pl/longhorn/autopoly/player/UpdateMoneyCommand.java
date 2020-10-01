package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMoneyCommand {

    private final PlayerRepository playerRepository;

    public void updateMoney(String playerId, int moneyAmount) {
        var players = playerRepository.get();
        var player = players.getPlayerById(playerId);
        player.setMoneyAmount(player.getMoneyAmount() + moneyAmount);
        playerRepository.save(players);
    }
}

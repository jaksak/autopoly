package pl.longhorn.autopoly.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.player.DeactivePlayerCommand;
import pl.longhorn.autopoly.player.NextPlayerCommand;
import pl.longhorn.autopoly.player.NextPlayerQuery;

@Service
@RequiredArgsConstructor
public class FinishTurnCommand {

    private final NextPlayerQuery nextPlayerQuery;
    private final NextPlayerCommand nextPlayerCommand;
    private final LockAnyFieldProcessor lockAnyFieldProcessor;
    private final DecreaseHouseLvlAnyFieldProcessor decreaseHouseLvlAnyFieldProcessor;
    private final DeactivePlayerCommand deactivePlayerCommand;

    public void finish() {
        nextPlayerQuery.get().ifPresent(player -> tryReduceNegativePlayerMoneyBalance());
        nextPlayerCommand.moveActionToNext();
    }

    private void tryReduceNegativePlayerMoneyBalance() {
        lockPropertyIfNegativeBalance();
        decreaseHouseLvlIfNegativeBalance();
        lockPropertyIfNegativeBalance();
        blockPlayerIfNegativeBalance();
    }

    private void blockPlayerIfNegativeBalance() {
        var player = nextPlayerQuery.get().orElseThrow();
        if (player.hasNegativeBalance()) {
            deactivePlayerCommand.deactivate(player.getId());
        }
    }

    private void decreaseHouseLvlIfNegativeBalance() {
        var player = nextPlayerQuery.get().orElseThrow();
        while (player.hasNegativeBalance()) {
            var hasPropertyToDecreaseLvl = decreaseHouseLvlAnyFieldProcessor.tryDecreaseHouseLvl(player.getId());
            if (hasPropertyToDecreaseLvl) {
                player = nextPlayerQuery.get().orElseThrow();
            } else {
                break;
            }
        }
    }

    private void lockPropertyIfNegativeBalance() {
        var player = nextPlayerQuery.get().orElseThrow();
        while (player.hasNegativeBalance()) {
            var hasPropertyToLock = lockAnyFieldProcessor.tryLockProperty(player.getId());
            if (hasPropertyToLock) {
                player = nextPlayerQuery.get().orElseThrow();
            } else {
                break;
            }
        }
    }


}

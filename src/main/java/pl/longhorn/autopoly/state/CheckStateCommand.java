package pl.longhorn.autopoly.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.action.auto.AutoActionCommand;

@Service
@RequiredArgsConstructor
public class CheckStateCommand {

    private static final int MAX_ACTION_AMOUNT = 2;
    private final AutoActionCommand autoActionCommand;

    public synchronized void checkState() {
        // TODO: checking date!
        boolean shouldContinue = true;
        for (int i = 0; i < MAX_ACTION_AMOUNT && shouldContinue; i++) {
            shouldContinue = autoActionCommand.doAutoAction();
        }
    }
}

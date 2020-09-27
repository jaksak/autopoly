package pl.longhorn.autopoly.player.type;

public class UserPlayerType implements PlayerType {
    @Override
    // TODO: add time interval to auto action
    public boolean shouldUseAutoAction() {
        return false;
    }
}

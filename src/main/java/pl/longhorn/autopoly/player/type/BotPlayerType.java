package pl.longhorn.autopoly.player.type;

public class BotPlayerType implements PlayerType {
    @Override
    public boolean shouldUseAutoAction() {
        return true;
    }
}

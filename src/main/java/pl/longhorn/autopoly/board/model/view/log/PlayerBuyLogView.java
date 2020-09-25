package pl.longhorn.autopoly.board.model.view.log;

public class PlayerBuyLogView implements BoardLogView {
    @Override
    public BoardLogType getType() {
        return BoardLogType.PLAYER_BUY;
    }
}

package pl.longhorn.autopoly.player;

public class FakePlayerFactory {

    public static Player active(String id) {
        return new Player(id, id, 0, id, null, null, true);
    }

    public static Player inactive(String id) {
        return new Player(id, id, -10, id, null, null, false);
    }
}

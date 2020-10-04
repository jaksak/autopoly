package pl.longhorn.autopoly.player;

import java.util.List;

public class FakePlayerFactory {

    public static Player active(String id) {
        return new Player(id, id, 0, id, List.of(), null, null, true);
    }

    public static Player inactive(String id) {
        return new Player(id, id, -10, id, List.of(), null, null, false);
    }
}

package pl.longhorn.autopoly.player.next;

import org.junit.jupiter.api.Test;
import pl.longhorn.autopoly.player.FakePlayerFactory;
import pl.longhorn.autopoly.player.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NextPlayerCalculatorTest {

    @Test
    public void shouldFindNextNearbyPlayer() {
        List<Player> players = List.of(FakePlayerFactory.active("A"), FakePlayerFactory.active("B"), FakePlayerFactory.active("C"));

        var result = new NextPlayerCalculator().getNext("B", players);

        assertEquals("C", result);
    }

    @Test
    public void shouldIgnoreInactive() {
        List<Player> players = List.of(FakePlayerFactory.active("A"), FakePlayerFactory.inactive("B"), FakePlayerFactory.active("C"));

        var result = new NextPlayerCalculator().getNext("A", players);

        assertEquals("C", result);
    }

    @Test
    public void shouldFindPreviousPlayer() {
        List<Player> players = List.of(FakePlayerFactory.active("A"), FakePlayerFactory.active("B"), FakePlayerFactory.active("C"));

        var result = new NextPlayerCalculator().getNext("C", players);

        assertEquals("A", result);
    }
}
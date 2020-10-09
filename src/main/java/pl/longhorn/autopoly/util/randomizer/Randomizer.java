package pl.longhorn.autopoly.util.randomizer;

import java.util.List;
import java.util.Optional;

public interface Randomizer {
    int nextInt(int max);

    int nextInt(int min, int max);

    <T> Optional<T> getRandom(List<T> objects);
}

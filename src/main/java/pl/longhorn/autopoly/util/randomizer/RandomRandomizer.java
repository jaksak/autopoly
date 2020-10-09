package pl.longhorn.autopoly.util.randomizer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
class RandomRandomizer implements Randomizer {

    private final Random random = new Random();

    @Override
    public int nextInt(int min, int max) {
        return nextInt(max - min) + min;
    }

    @Override
    public <T> Optional<T> getRandom(List<T> objects) {
        if (objects.isEmpty()) {
            return Optional.empty();
        } else {
            var object = objects.get(random.nextInt(objects.size()));
            return Optional.of(object);
        }
    }

    @Override
    public int nextInt(int max) {
        return random.nextInt(max);
    }
}

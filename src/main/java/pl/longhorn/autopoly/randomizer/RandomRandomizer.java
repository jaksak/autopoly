package pl.longhorn.autopoly.randomizer;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class RandomRandomizer implements Randomizer {

    private final Random random = new Random();

    @Override
    public int nextInt(int min, int max) {
        return nextInt(max) + min;
    }

    @Override
    public int nextInt(int max) {
        return random.nextInt(max);
    }
}

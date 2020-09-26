package pl.longhorn.autopoly.randomizer;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomRandomizer implements Randomizer {

    private final Random random = new Random();

    @Override
    public int nextInt(int maxInt) {
        return random.nextInt(maxInt);
    }
}

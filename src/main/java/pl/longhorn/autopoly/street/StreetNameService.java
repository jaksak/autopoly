package pl.longhorn.autopoly.street;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StreetNameService {

    private final StreetNames streetNames = new StreetNames();

    @SneakyThrows
    public String getRandom() {
        Random random = new Random();
        return streetNames.getNames()[random.nextInt(streetNames.getNames().length)];
    }
}

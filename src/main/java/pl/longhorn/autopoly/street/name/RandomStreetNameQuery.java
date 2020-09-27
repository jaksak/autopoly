package pl.longhorn.autopoly.street.name;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomStreetNameQuery {

    private final StreetNameRepository streetNameRepository;

    @SneakyThrows
    public String getRandom() {
        return streetNameRepository.getRandom().getName();
    }
}

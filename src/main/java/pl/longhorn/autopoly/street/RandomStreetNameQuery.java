package pl.longhorn.autopoly.street;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomStreetNameQuery {

    private final StreetNamesRepository streetNamesRepository;

    @SneakyThrows
    public String getRandom() {
        return streetNamesRepository.getRandom().getName();
    }
}

package pl.longhorn.autopoly.nick;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NickQuery {

    private final NickRepository repository;

    public String getRandom() {
        return repository.getRandom().getNickValue();
    }
}

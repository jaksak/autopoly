package pl.longhorn.autopoly.id;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.nick.NickQuery;

@Service
@RequiredArgsConstructor
public class NickIdFactory implements IdFactory {

    private final NickQuery nickQuery;

    @Override
    public String generate() {
        return nickQuery.getRandom();
    }
}

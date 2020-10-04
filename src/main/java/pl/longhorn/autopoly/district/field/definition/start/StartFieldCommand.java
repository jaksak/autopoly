package pl.longhorn.autopoly.district.field.definition.start;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.util.id.IdFactory;

@Service
@RequiredArgsConstructor
public class StartFieldCommand {

    private final IdFactory idFactory;

    public StartField prepare() {
        return new StartField(idFactory.generate());
    }
}

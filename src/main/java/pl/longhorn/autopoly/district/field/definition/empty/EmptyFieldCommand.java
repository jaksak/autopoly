package pl.longhorn.autopoly.district.field.definition.empty;

import lombok.RequiredArgsConstructor;
import pl.longhorn.autopoly.util.id.IdFactory;

@RequiredArgsConstructor
public class EmptyFieldCommand {

    private final IdFactory idFactory;

    public EmptyField prepare() {
        return new EmptyField(idFactory.generate());
    }
}

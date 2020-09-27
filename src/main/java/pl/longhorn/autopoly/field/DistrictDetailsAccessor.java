package pl.longhorn.autopoly.field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistrictDetailsAccessor {

    private final DistrictDetailsQuery query;
    private final DistrictDetailsCommand command;

    public DistrictDetails get() {
        var result = query.get();
        if (result == null) {
            return command.prepareFields();
        } else {
            return result;
        }
    }
}

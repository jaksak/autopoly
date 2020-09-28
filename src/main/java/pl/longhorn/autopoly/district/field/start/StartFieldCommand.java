package pl.longhorn.autopoly.district.field.start;

import org.springframework.stereotype.Service;

@Service
public class StartFieldCommand {

    public StartField prepare(String id) {
        return new StartField(id);
    }
}

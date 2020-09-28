package pl.longhorn.autopoly.id;

import org.springframework.stereotype.Service;

@Service
public class IntegerIdFactory implements IdFactory {

    private int i = 0;

    @Override
    public String generate() {
        return i++ + "";
    }
}

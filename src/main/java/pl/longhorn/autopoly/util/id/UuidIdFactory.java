package pl.longhorn.autopoly.util.id;

import java.util.UUID;

//@Service
class UuidIdFactory implements IdFactory {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}

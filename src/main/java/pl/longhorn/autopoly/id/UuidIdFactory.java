package pl.longhorn.autopoly.id;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class UuidIdFactory implements IdFactory {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}

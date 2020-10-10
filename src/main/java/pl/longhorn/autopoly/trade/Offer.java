package pl.longhorn.autopoly.trade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Offer {
    private final String player;
    private final List<String> fields;
    private final int money;
}

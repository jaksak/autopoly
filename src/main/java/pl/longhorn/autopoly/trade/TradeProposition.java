package pl.longhorn.autopoly.trade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TradeProposition {
    private final String id;
    private final Offer offer1;
    private final Offer offer2;
}

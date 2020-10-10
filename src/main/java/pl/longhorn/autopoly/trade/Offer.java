package pl.longhorn.autopoly.trade;

import lombok.Getter;

import java.util.List;

@Getter
public class Offer {
    private String player;
    private List<String> fields;
    private int money;
}

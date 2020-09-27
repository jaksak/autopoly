package pl.longhorn.autopoly.player;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class Player {
    private final String id;
    private final String name;
    private final int moneyAmount;
    private final String currentFieldId;
    private final List<String> ownedFields;

    public abstract boolean shouldUseAutoAction();
}

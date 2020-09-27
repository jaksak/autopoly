package pl.longhorn.autopoly.player;

import java.util.List;

class UserPlayer extends Player {

    public UserPlayer(String id, String name, int moneyAmount, String currentFieldId, List<String> ownedFields) {
        super(id, name, moneyAmount, currentFieldId, ownedFields);
    }

    @Override
    public boolean shouldUseAutoAction() {
        // TODO: add logic: if inactivity - yes
        return false;
    }
}

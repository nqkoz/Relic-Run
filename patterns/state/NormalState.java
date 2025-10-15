package patterns.state;

import model.characters.Player;

public class NormalState implements StatusState {
    @Override
    public String name() {
        return "normal";
    }

    @Override
    public void onTurn(Player p) {
        /* aucun effet */ }
}
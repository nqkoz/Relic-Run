package patterns.state;


import model.characters.Player;


public interface StatusState {
String name();
default void onTurn(Player p) { /* hooks par état */ }
}
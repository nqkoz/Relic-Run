package patterns.state;


import model.characters.Player;


public class PoisonedState implements StatusState {
private int turns = 3;
@Override public String name() { return "empoisonné"; }
@Override public void onTurn(Player p) {
if (turns-- > 0) {
p.damage(2);
System.out.println("Le poison vous ronge (-2 PV). Reste " + Math.max(0, turns) + " tour(s).");
} else {
p.setStatus(new NormalState());
System.out.println("Vous n'êtes plus empoisonné.");
}
}
}
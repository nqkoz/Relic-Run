package model.characters;


public class Goblin extends Enemy {
public Goblin(String name) { super(name, 30); }
@Override public int attack(Player player) {
int dmg = 3 + rnd.nextInt(5);
player.damage(dmg);
return dmg;
}
}
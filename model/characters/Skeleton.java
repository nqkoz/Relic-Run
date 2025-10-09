package jeuprojet.model.characters;


public class Skeleton extends Enemy {
public Skeleton(String name) { super(name, 45); }
@Override public int attack(Player player) {
int dmg = 5 + rnd.nextInt(6);
player.damage(dmg);
return dmg;
}
}
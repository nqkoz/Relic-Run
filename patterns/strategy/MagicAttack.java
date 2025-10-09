package jeuprojet.patterns.strategy;


import jeuprojet.model.characters.Enemy;
import jeuprojet.model.characters.Player;


import java.util.concurrent.ThreadLocalRandom;


public class MagicAttack implements AttackStrategy {
@Override public int attack(Player player, Enemy enemy) {
int dmg = ThreadLocalRandom.current().nextInt(10, 18);
enemy.damage(dmg);
// petit contrecoup
player.damage(1);
return dmg;
}
}
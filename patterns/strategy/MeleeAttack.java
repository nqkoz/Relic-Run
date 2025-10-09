package jeuprojet.patterns.strategy;


import jeuprojet.model.characters.Enemy;
import jeuprojet.model.characters.Player;


import java.util.concurrent.ThreadLocalRandom;


public class MeleeAttack implements AttackStrategy {
@Override public int attack(Player player, Enemy enemy) {
int dmg = ThreadLocalRandom.current().nextInt(6, 12);
enemy.damage(dmg);
return dmg;
}
}
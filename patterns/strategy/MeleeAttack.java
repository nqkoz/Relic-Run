package patterns.strategy;

import model.characters.Enemy;
import model.characters.Player;

import java.util.concurrent.ThreadLocalRandom;

public class MeleeAttack implements AttackStrategy {
    @Override
    public int attack(Player player, Enemy enemy) {
        int dmg = ThreadLocalRandom.current().nextInt(6, 12);
        enemy.damage(dmg);
        return dmg;
    }
}
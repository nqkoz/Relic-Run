package patterns.strategy;


import model.characters.Enemy;
import model.characters.Player;


public interface AttackStrategy {
int attack(Player player, Enemy enemy);
}
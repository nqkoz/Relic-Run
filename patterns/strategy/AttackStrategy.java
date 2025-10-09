package jeuprojet.patterns.strategy;


import jeuprojet.model.characters.Enemy;
import jeuprojet.model.characters.Player;


public interface AttackStrategy {
int attack(Player player, Enemy enemy);
}
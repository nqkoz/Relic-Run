package jeuprojet.model.characters;


import jeuprojet.model.Item;


import java.util.Random;


public abstract class Enemy extends CharacterBase {
protected final Random rnd = new Random();
private Item loot;


protected Enemy(String name, int hp) { super(name, hp); }
public abstract int attack(Player player);
public Item getLoot() { return loot; }
public void setLoot(Item loot) { this.loot = loot; }
}
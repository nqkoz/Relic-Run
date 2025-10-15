package model.characters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Item;
import model.Room;       // <— important
import model.UseContext;
import patterns.state.StatusState;
import patterns.state.NormalState;
import patterns.strategy.AttackStrategy;
import patterns.strategy.MeleeAttack;


import java.util.*;


public class Player extends CharacterBase {
private final List<Item> inventory = new ArrayList<>();
private Room currentRoom;
private StatusState status = new NormalState();
private AttackStrategy attackStrategy = new MeleeAttack();


public Player(String name, int health) { super(name, health); }


public Room getCurrentRoom() { return currentRoom; }
public void setCurrentRoom(Room r) { currentRoom = r; }


public AttackStrategy getAttackStrategy() { return attackStrategy; }
public void setAttackStrategy(AttackStrategy s) { this.attackStrategy = s; }


public StatusState getStatus() { return status; }
public void setStatus(StatusState s) { this.status = s; }


public boolean move(String dir) {
Room next = currentRoom.next(dir);
if (next == null) return false;
currentRoom = next; notifyObservers("move:"+next.getId()); return true;
}


public boolean take(String itemName) {
Item i = currentRoom.removeItemByName(itemName);
if (i == null) return false;
inventory.add(i); notifyObservers("take:"+i.id());
System.out.println("Pris: " + i.name());
return true;
}


public boolean drop(String itemName) {
Item found = null;
for (Item i : inventory) if (i.nameEquals(itemName)) { found = i; break; }
if (found == null) return false;
inventory.remove(found);
currentRoom.addItem(found); notifyObservers("drop:"+found.id());
System.out.println("Posé: " + found.name());
return true;
}


public boolean use(String itemName, String target) {
Item found = null;
for (Item i : inventory) if (i.nameEquals(itemName)) { found = i; break; }
if (found == null) return false;
return found.use(new UseContext(this, currentRoom, target));
}


public boolean hasItem(String idOrName) {
for (Item i : inventory) if (i.nameEquals(idOrName)) return true; return false;
}


public String inventoryString() {
if (inventory.isEmpty()) return "Inventaire vide.";
StringBuilder sb = new StringBuilder("Inventaire: ");
for (Item i : inventory) sb.append(i.name()).append(" ");
return sb.toString();
}
}
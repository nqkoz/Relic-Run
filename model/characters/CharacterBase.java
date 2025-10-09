package jeuprojet.model.characters;


import jeuprojet.patterns.observer.Observer;
import jeuprojet.patterns.observer.Subject;


import java.util.ArrayList;
import java.util.List;


public abstract class CharacterBase implements Subject {
protected String name;
protected int health;
protected final List<Observer> observers = new ArrayList<>();


public CharacterBase(String name, int health) { this.name = name; this.health = health; }
public String getName() { return name; }
public int getHealth() { return health; }
public boolean isAlive() { return health > 0; }


public void damage(int amount) { this.health = Math.max(0, health - amount); notifyObservers("health:"+health); }
public void heal(int amount) { this.health = Math.min(100, health + amount); notifyObservers("health:"+health); }


@Override public void addObserver(Observer o) { observers.add(o); }
@Override public void removeObserver(Observer o) { observers.remove(o); }
@Override public void notifyObservers(String event) { for (Observer o: observers) o.update(this, event); }
}
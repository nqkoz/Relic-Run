package jeuprojet.model;


import jeuprojet.model.characters.Player;


public class UseContext {
public final Player player;
public final Room room;
public final String target; // peut être id de salle/ennemi/objet


public UseContext(Player player, Room room, String target) {
this.player = player; this.room = room; this.target = target;
}
}
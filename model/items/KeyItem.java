package jeuprojet.model.items;


import jeuprojet.model.Item;
import jeuprojet.model.Room;
import jeuprojet.model.UseContext;


public class KeyItem extends Item {
private final String unlockRoomId;
public KeyItem(String id, String name, String unlockRoomId) { super(id, name); this.unlockRoomId = unlockRoomId; }
@Override public boolean use(UseContext ctx) {
Room r = ctx.room;
if (unlockRoomId != null && ctx.target != null && ctx.target.equalsIgnoreCase(unlockRoomId)) {
System.out.println("Vous déverrouillez la salle " + ctx.target + ". Un passage s'ouvre au nord !");
// Simplement ajouter une sortie vers un lieu spécial si pas déjà là
return true;
}
System.out.println("Cette clé n'a aucun effet ici.");
return false;
}
}
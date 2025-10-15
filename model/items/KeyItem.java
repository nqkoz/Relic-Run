package model.items;


import model.Item;
import model.Room;
import model.UseContext;


public class KeyItem extends Item {
private final String unlockRoomId;
public KeyItem(String id, String name, String unlockRoomId) { super(id, name); this.unlockRoomId = unlockRoomId; }
    @Override public boolean use(UseContext ctx) {
    Room r = ctx.room;
    if (unlockRoomId != null && ctx.target != null && ctx.target.equalsIgnoreCase(unlockRoomId)) {
        System.out.println("Vous déverrouillez la salle " + ctx.target + ". Un passage s'ouvre au nord !");
        return true;
    }
        System.out.println("Cette clé n'a aucun effet ici.");
        return false;
    }
}
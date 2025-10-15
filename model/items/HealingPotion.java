package model.items;


import model.Item;
import model.UseContext;
import patterns.singleton.GameState;


public class HealingPotion extends Item {
private final int heal;
public HealingPotion(String id, String name, int heal) { super(id, name); this.heal = heal; }
@Override public boolean use(UseContext ctx) {
var p = GameState.getInstance().getPlayer();
int before = p.getHealth();
p.heal(heal);
System.out.println("Vous buvez " + name() + " (+"+heal+") => " + before + " -> " + p.getHealth());
return true;
}
}
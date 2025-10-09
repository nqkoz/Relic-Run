package jeuprojet.model.items;


import jeuprojet.model.Item;
import jeuprojet.model.UseContext;


public class SimpleItem extends Item {
public SimpleItem(String id, String name) { super(id, name); }
@Override public boolean use(UseContext ctx) { return false; }
}
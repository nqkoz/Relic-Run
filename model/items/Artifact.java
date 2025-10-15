package model.items;


import model.Item;
import model.UseContext;


public class Artifact extends Item {
public Artifact(String id, String name) { super(id, name); }
@Override public boolean use(UseContext ctx) {
System.out.println("L'artefact vibre entre vos mains…");
return true;
}
}
package jeuprojet.model.items;


import jeuprojet.model.Item;
import jeuprojet.model.UseContext;


public class Artifact extends Item {
public Artifact(String id, String name) { super(id, name); }
@Override public boolean use(UseContext ctx) {
System.out.println("L'artefact vibre faiblement entre vos mains…");
return true;
}
}
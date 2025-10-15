package model.characters;


public class NPC {
private final String name;
private final String dialog;


public NPC(String name, String dialog) { this.name = name; this.dialog = dialog; }
public String getName() { return name; }
public String talk() { return name + ": \"" + dialog + "\""; }
}
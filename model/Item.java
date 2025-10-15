package model;

public abstract class Item {
    private final String id;
    private final String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public boolean nameEquals(String n) {
        return name.equalsIgnoreCase(n) || id.equalsIgnoreCase(n);
    }

    public abstract boolean use(UseContext ctx);
}
package model;

import model.characters.Enemy;
import model.characters.NPC;

import java.util.*;

public class Room {
    private final String id;
    private final String name;
    private final String description;
    private final Map<String, Room> exits = new HashMap<>(); // agrégation
    private final List<Item> items = new ArrayList<>();
    private final List<NPC> npcs = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();

    public Room(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void connect(String dir, Room dest) {
        exits.put(dir.toLowerCase(), dest);
    }

    public Room next(String dir) {
        return exits.get(dir.toLowerCase());
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append("== ").append(name).append(" ==\n").append(description).append("\n");
        if (!items.isEmpty()) {
            sb.append("Objets: ");
            for (Item it : items)
                sb.append(it.name()).append(" ");
            sb.append("\n");
        }
        if (!npcs.isEmpty()) {
            sb.append("PNJ: ");
            for (NPC n : npcs)
                sb.append(n.getName()).append(" ");
            sb.append("\n");
        }
        if (!enemies.isEmpty()) {
            sb.append("Ennemis: ");
            for (Enemy e : enemies)
                sb.append(e.getName()).append("(PV:").append(e.getHealth()).append(") ");
            sb.append("\n");
        }
        if (!exits.isEmpty()) {
            sb.append("Sorties: ").append(String.join(", ", exits.keySet())).append("\n");
        }
        return sb.toString();
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public Item removeItemByName(String name) {
        for (Iterator<Item> it = items.iterator(); it.hasNext();) {
            Item i = it.next();
            if (i.nameEquals(name)) {
                it.remove();
                return i;
            }
        }
        return null;
    }

    public Item findItem(String name) {
        for (Item i : items)
            if (i.nameEquals(name))
                return i;
        return null;
    }

    public void addNPC(NPC n) {
        npcs.add(n);
    }

    public NPC findNPC(String name) {
        for (NPC n : npcs)
            if (n.getName().equalsIgnoreCase(name))
                return n;
        return null;
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public Enemy findEnemy(String name) {
        for (Enemy e : enemies)
            if (e.getName().equalsIgnoreCase(name))
                return e;
        return null;
    }

    public void removeEnemy(Enemy e) {
        enemies.remove(e);
    }
}
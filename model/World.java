package model;


import model.characters.Player;


import java.util.HashMap;
import java.util.Map;


public class World {
private final Map<String, Room> rooms = new HashMap<>();
private Player player;


public void addRoom(Room r) { rooms.put(r.getId().toLowerCase(), r); }
public Room getRoom(String id) { return rooms.get(id.toLowerCase()); }
public Map<String, Room> getRooms() { return rooms; }


public Player getPlayer() { return player; }
public void setPlayer(Player player) { this.player = player; }
}
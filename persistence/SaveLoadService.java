package jeuprojet.persistence;

import jeuprojet.model.Room;
import jeuprojet.model.World;
import jeuprojet.model.characters.Player;
import jeuprojet.patterns.factory.GameFactory;
import jeuprojet.patterns.singleton.GameState;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SaveLoadService {
    public void save(String filename) {
        try {
            World w = GameState.getInstance().getWorld();
            Player p = GameState.getInstance().getPlayer();
            Properties props = new Properties();
            props.setProperty("player.name", p.getName());
            props.setProperty("player.hp", String.valueOf(p.getHealth()));
            props.setProperty("player.room", p.getCurrentRoom().getId());
            try (FileOutputStream fos = new FileOutputStream(filename)) {
                props.store(fos, "SAVE JEUPROJET");
            }
            System.out.println("Sauvegardé dans " + filename);
        } catch (IOException e) {
            throw new RuntimeException("Échec de sauvegarde: " + e.getMessage());
        }
    }

    public void load(String filename, GameFactory factory) {
        try {
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(filename)) {
                props.load(fis);
            }
            World w = factory.buildWorld();
            GameState.getInstance().setWorld(w);
            Player p = w.getPlayer();
            GameState.getInstance().setPlayer(p);

            // PV
            String hp = props.getProperty("player.hp");
            if (hp != null) {
                int target = Integer.parseInt(hp);
                if (target < p.getHealth()) p.damage(p.getHealth() - target);
                else if (target > p.getHealth()) p.heal(target - p.getHealth());
            }

            // Salle
            String room = props.getProperty("player.room");
            if (room != null) {
                Room r = w.getRoom(room);
                if (r != null) p.setCurrentRoom(r);
            }
            System.out.println("Sauvegarde chargée depuis " + filename);
        } catch (IOException e) {
            throw new RuntimeException("Échec de chargement: " + e.getMessage());
        }
    }
}

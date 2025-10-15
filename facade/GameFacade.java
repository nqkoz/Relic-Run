package facade;

import model.*;
import model.characters.*;
import patterns.factory.GameFactory;
import patterns.singleton.GameState;
import persistence.SaveLoadService;
import patterns.strategy.AttackStrategy;

public class GameFacade {
	private final GameFactory factory = new GameFactory();
	private final SaveLoadService saveLoad = new SaveLoadService();

	public World createWorld() {
		return factory.buildWorld();
	}

	public void describeLocation() {
		Player p = GameState.getInstance().getPlayer();
		Room r = p.getCurrentRoom();
		System.out.println(r.describe());
	}

	public void go(String direction) {
		if (direction == null) {
			System.out.println("Précisez une direction.");
			return;
		}
		Player p = GameState.getInstance().getPlayer();
		if (p.move(direction)) {
			describeLocation();
		} else {
			System.out.println("Impossible d'aller vers " + direction + ".");
		}
	}

	public void take(String itemName) {
		Player p = GameState.getInstance().getPlayer();
		if (!p.take(itemName))
			System.out.println("Aucun objet nommé '" + itemName + "' ici.");
	}

	public void drop(String itemName) {
		Player p = GameState.getInstance().getPlayer();
		if (!p.drop(itemName))
			System.out.println("Vous n'avez pas '" + itemName + "'.");
	}

	public void showInventory() {
		Player p = GameState.getInstance().getPlayer();
		System.out.println(p.inventoryString());
	}

	public void use(String itemName, String target) {
		Player p = GameState.getInstance().getPlayer();
		if (!p.use(itemName, target))
			System.out.println("Utilisation impossible.");
	}

	public void talk(String npcName) {
		if (npcName == null) {
			System.out.println("À qui parler ?");
			return;
		}
		Player p = GameState.getInstance().getPlayer();
		NPC npc = p.getCurrentRoom().findNPC(npcName);
		if (npc == null) {
			System.out.println("Personne nommé '" + npcName + "' ici.");
			return;
		}
		System.out.println(npc.talk());
	}

	public void attack(String enemyName) {
		Player p = GameState.getInstance().getPlayer();
		Enemy e = p.getCurrentRoom().findEnemy(enemyName);
		if (e == null) {
			System.out.println("Aucun ennemi '" + enemyName + "' ici.");
			return;
		}
		AttackStrategy strat = p.getAttackStrategy();
		int dmg = strat.attack(p, e);
		System.out.println("Vous infligez " + dmg + " dégâts à " + e.getName() + ".");
		if (!e.isAlive()) {
			System.out.println(e.getName() + " est vaincu!");
			p.getCurrentRoom().removeEnemy(e);
			// Drop loot si dispo
			if (e.getLoot() != null) {
				p.getCurrentRoom().addItem(e.getLoot());
				System.out.println(e.getName() + " laisse tomber: " + e.getLoot().name());
			}
			return;
		} // Riposte
		int edmg = e.attack(p);
		System.out.println(e.getName() + " vous frappe pour " + edmg + ". PV=" + p.getHealth());
	}

	public void save(String filename) {
		saveLoad.save(filename);
	}

	public void load(String filename) {
		saveLoad.load(filename, factory);
	}

	public boolean checkWinCondition() {
		World w = GameState.getInstance().getWorld();
		Player p = w.getPlayer();
		// Gagne si l'artefact ancien est dans l'inventaire ET joueur de retour à la
		// salle de départ
		boolean hasArtifact = p.hasItem("artefact");
		boolean atStart = "entrée".equalsIgnoreCase(p.getCurrentRoom().getId());
		return hasArtifact && atStart;
	}
}
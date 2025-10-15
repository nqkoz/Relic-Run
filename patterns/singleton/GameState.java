package patterns.singleton;

import model.World;
import model.characters.Player;

public class GameState {
	private static GameState INSTANCE;
	private World world;
	private Player player;

	private GameState() {
	}

	public static GameState getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameState();
		return INSTANCE;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
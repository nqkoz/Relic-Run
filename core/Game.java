package jeuprojet.core;

import jeuprojet.facade.GameFacade;
import jeuprojet.model.World;
import jeuprojet.model.characters.Player;
import jeuprojet.patterns.singleton.GameState;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final CommandParser parser = new CommandParser();
    private final GameFacade facade;

    public Game() {
        // Création du monde via la façade (qui délègue à la factory)
        this.facade = new GameFacade();
        World world = facade.createWorld();
        GameState.getInstance().setWorld(world);
        Player player = world.getPlayer();
        GameState.getInstance().setPlayer(player);
    }

    public void run() {
        System.out.println("Bienvenue dans JEUPROJET – tapez `help` pour l'aide.");
        facade.describeLocation();
        while (true) {
            if (facade.checkWinCondition()) {
                System.out.println("\n🎉 Victoire ! Vous avez accompli votre quête. Fin du jeu.");
                return;
            }
            if (!GameState.getInstance().getPlayer().isAlive()) {
                System.out.println("\n☠️ Vous êtes mort… Game Over.");
                return;
            }
            System.out.print("\n> ");
            String line = scanner.nextLine();
            Command cmd = parser.parse(line);
            if (cmd == null) {
                System.out.println("Commande inconnue. Tapez `help`.");
                continue;
            }
            if (cmd.type() == CommandType.QUIT) {
                System.out.println("Au revoir!");
                return;
            }
            try {
                switch (cmd.type()) {
                    case LOOK -> facade.describeLocation();
                    case GO -> facade.go(cmd.arg1());
                    case TAKE -> facade.take(cmd.arg1());
                    case DROP -> facade.drop(cmd.arg1());
                    case INVENTORY -> facade.showInventory();
                    case USE -> facade.use(cmd.arg1(), cmd.arg2());
                    case TALK -> facade.talk(cmd.arg1());
                    case ATTACK -> facade.attack(cmd.arg1());
                    case SAVE -> facade.save(cmd.arg1() == null ? "save.txt" : cmd.arg1());
                    case LOAD -> facade.load(cmd.arg1() == null ? "save.txt" : cmd.arg1());
                    case HELP -> System.out.println(Help.TEXT);
                }
            } catch (Exception e) {
                System.out.println("[Erreur] " + e.getMessage());
            }
        }
    }
}

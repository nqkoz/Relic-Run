package jeuprojet.core;

import java.util.Locale;

public class CommandParser {
	public Command parse(String line) {
		if (line == null)
			return null;
		String s = line.trim();
		if (s.isEmpty())
			return null;
		String[] tokens = s.split("\\s+");
		String verb = tokens[0].toLowerCase(Locale.ROOT);
		String arg1 = tokens.length > 1 ? tokens[1] : null;
		String arg2 = tokens.length > 2 ? String.join(" ", java.util.Arrays.copyOfRange(tokens, 2, tokens.length))
				: null;
		return switch (verb) {
		case "regarder", "look", "voir" -> new Command(CommandType.LOOK, null, null);
		case "aller", "go" -> new Command(CommandType.GO, arg1, null);
		case "prendre", "take" -> new Command(CommandType.TAKE, arg1, null);
		case "poser", "drop" -> new Command(CommandType.DROP, arg1, null);
		case "inv", "inventaire", "inventory" -> new Command(CommandType.INVENTORY, null, null);
		case "utiliser", "use" -> new Command(CommandType.USE, arg1, arg2);
		case "parler", "talk" -> new Command(CommandType.TALK, arg1, null);
		case "attaquer", "attack" -> new Command(CommandType.ATTACK, arg1, null);
		case "sauver", "save" -> new Command(CommandType.SAVE, arg1, null);
		case "charger", "load" -> new Command(CommandType.LOAD, arg1, null);
		case "aide", "help" -> new Command(CommandType.HELP, null, null);
		case "quitter", "quit", "exit" -> new Command(CommandType.QUIT, null, null);
		default -> null;
		};
	}
}
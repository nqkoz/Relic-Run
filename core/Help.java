package jeuprojet.core;

public class Help {
	public static final String TEXT = """
			Commandes utiles:
			regarder : décrire la pièce
			aller <nord|sud|est|ouest>: se déplacer
			prendre <objet> : ramasser un objet
			poser <objet> : déposer un objet
			utiliser <objet> [cible] : utiliser/combiner (ex: utiliser cle salle)
			parler <pnj> : discuter avec un PNJ
			attaquer <ennemi> : attaquer un ennemi dans la pièce
			inventaire : afficher l'inventaire
			sauver [fichier] : sauvegarder l'état (défaut: save.txt)
			charger [fichier] : charger une sauvegarde
			aide : cette aide
			quitter : quitter le jeu
			""";
}
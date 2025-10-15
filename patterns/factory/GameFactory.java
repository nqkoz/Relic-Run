package patterns.factory;

import model.*;
import model.characters.*;
import model.items.*;

public class GameFactory {
    public World buildWorld() {
        World w = new World();
        // 10+ pièces
        Room entree = new Room("entrée", "Entrée des Ruines", "Une arche effondrée mène à des couloirs sombres.");
        Room hall   = new Room("hall", "Grand Hall", "Colonnes brisées et poussière.");
        Room armu   = new Room("armurerie", "Armurerie", "Des râteliers rouillés.");
        Room bibli  = new Room("bibli", "Bibliothèque", "Des étagères à moitié calcinées.");
        Room labo   = new Room("labo", "Laboratoire", "Fioles brisées et odeur âcre.");
        Room dort   = new Room("dortoir", "Dortoir", "Lits renversés.");
        Room refec  = new Room("réfectoire", "Réfectoire", "Tables longues et couverts tordus.");
        Room catac  = new Room("catacombes", "Catacombes", "Couloirs étroits bordés d'ossements.");
        Room salle  = new Room("salle", "Salle Scellée", "Une porte lourde gravée de runes.");
        Room sanct  = new Room("sanctuaire", "Sanctuaire", "L'artefact ancien repose sur un piédestal.");

        // connexions
        entree.connect("est", hall);
        hall.connect("ouest", entree);
        hall.connect("nord", bibli);
        hall.connect("sud", refec);
        hall.connect("est", armu);
        bibli.connect("sud", hall);
        bibli.connect("est", labo);
        labo.connect("ouest", bibli);
        refec.connect("nord", hall);
        refec.connect("est", dort);
        dort.connect("ouest", refec);
        armu.connect("ouest", hall);
        armu.connect("est", catac);
        catac.connect("ouest", armu);
        catac.connect("nord", salle);
        salle.connect("nord", sanct);
        sanct.connect("sud", salle);

        // Items
        hall.addItem(new HealingPotion("potion", "potion", 15));
        armu.addItem(new SimpleItem("dague", "dague"));
        bibli.addItem(new SimpleItem("parchemin", "parchemin"));
        dort.addItem(new SimpleItem("corde", "corde"));
        salle.addItem(new SimpleItem("cle", "cle"));

        // PNJ
        bibli.addNPC(new NPC("sage", "Les catacombes abritent un gardien. Une dague bien affutée peut te sauver."));
        refec.addNPC(new NPC("cuisinier", "J'ai vu des squelettes trainant près de l'armurerie…"));

        // Ennemis
        catac.addEnemy(new Skeleton("squelette"));
        salle.addEnemy(new Goblin("gobelin"));

        // Boss + loot
        Skeleton boss = new Skeleton("seigneur-os");
        boss.setLoot(new Artifact("artefact", "artefact"));
        sanct.addEnemy(boss);

        // Joueur
        Player p = new Player("héros", 100);
        p.setCurrentRoom(entree);

        // Observer simple
        p.addObserver((src, evt) -> {
            if (evt.startsWith("health:")) System.out.println("[Observer] PV: " + evt.substring(7));
            else if (evt.startsWith("take:")) System.out.println("[Observer] Objet pris: " + evt.substring(5));
            else if (evt.startsWith("drop:")) System.out.println("[Observer] Objet posé: " + evt.substring(5));
            else if (evt.startsWith("move:")) System.out.println("[Observer] Déplacement vers: " + evt.substring(5));
        });

        w.addRoom(entree); w.addRoom(hall); w.addRoom(armu); w.addRoom(bibli); w.addRoom(labo);
        w.addRoom(dort); w.addRoom(refec); w.addRoom(catac); w.addRoom(salle); w.addRoom(sanct);
        w.setPlayer(p);
        return w;
    }
}

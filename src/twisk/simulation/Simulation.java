package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {
    KitC environnement;


    public Simulation() {
        environnement = new KitC();
    }

    public void simuler(Monde monde) {
        environnement.creerFichier(monde.toC().toString());
        environnement.compiler();
        environnement.construireLaLibrairie();
    }
}

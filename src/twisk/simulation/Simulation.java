package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {
    KitC environnement;


    public Simulation() {
        environnement = new KitC();
    }


    public native int[] start_simulation();

    public native int[] ou_sont_les_clients();

    public native void nettoyage();

    public void simuler(Monde monde) {
        environnement.creerFichier(monde.toC().toString());
        environnement.compiler();
        environnement.construireLaLibrairie();
    }
}

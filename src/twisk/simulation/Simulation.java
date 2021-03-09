package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {
    KitC environnement;


    public Simulation() {
        environnement = new KitC();
    }

    public void simuler(Monde monde) {
        System.out.println(monde.toString());
        monde.toC();
    }
}

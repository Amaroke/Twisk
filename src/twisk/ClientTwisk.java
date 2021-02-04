package twisk;

import twisk.monde.Activite;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {

    public static void main(String[] args) {

        Monde m = new Monde();
        Simulation s = new Simulation();

        Activite parking = new Activite("Parking");
        Guichet gp = new Guichet("Guichet", 2500);
        Activite baladezoo = new Activite("Balade au zoo");
        Guichet gt = new Guichet("Guichet", 2);
        Activite tobbogan = new Activite("Toboggan");

        m.ajouter(parking, gp, baladezoo, gt, tobbogan);
        m.aCommeEntree(gp, baladezoo);
        gp.ajouterSuccesseur(parking);
        parking.ajouterSuccesseur(baladezoo);
        baladezoo.ajouterSuccesseur(gt);
        gt.ajouterSuccesseur(tobbogan);
        m.aCommeSortie(tobbogan);

        s.simuler(m);

    }
}

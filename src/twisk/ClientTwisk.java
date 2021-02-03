package twisk;

import twisk.monde.Activite;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {

    public static void main(String[] args) {
        Monde m = new Monde();
        Simulation s = new Simulation();

        Activite parking = new Activite("parking");
        Guichet gp = new Guichet("guichet", 2500);
        Activite baladezoo = new Activite("balade au zoo");
        Guichet gt = new Guichet("guichet", 2);
        Activite tobbogan = new Activite("toboggan");

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

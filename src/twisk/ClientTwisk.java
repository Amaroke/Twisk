package twisk;

import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

/**
 * Classe ClientTwisk
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class ClientTwisk {

    public static void main(String[] args) {
        Monde monde = new Monde();

        Activite zoo = new Activite("balade au zoo", 3, 1);
        Guichet guichet = new Guichet("accès au toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);

        zoo.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(tob);

        monde.ajouter(zoo, guichet, tob);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        Simulation s = new Simulation();
        s.setNbClients(5);
        s.simuler(monde);
    }
}

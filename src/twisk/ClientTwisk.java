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

        // Premier Monde
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

        // Deuxième monde
        Monde monde2 = new Monde();

        Activite zoo2 = new Activite("balade au zoo", 3, 1);
        Guichet guichet2 = new Guichet("accès au toboggan", 2);
        Activite tob2 = new ActiviteRestreinte("toboggan", 2, 1);
        Activite tourniquet = new Activite("tourniquet", 3, 2);
        Activite parachute = new Activite("parachute", 4, 1);

        zoo2.ajouterSuccesseur(guichet2);
        guichet2.ajouterSuccesseur(tob2);
        tob2.ajouterSuccesseur(tourniquet);
        tourniquet.ajouterSuccesseur(parachute);

        monde2.ajouter(zoo2, guichet2, tob2, tourniquet, parachute);

        monde2.aCommeEntree(zoo2);
        monde2.aCommeSortie(parachute);

        Simulation s2 = new Simulation();
        s2.setNbClients(5);
        s2.simuler(monde2);
    }
}

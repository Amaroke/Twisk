package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.KitC;

/**
 * Classe Simulation
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class Simulation {

    KitC environnement;
    int nbClients;

    /**
     * Constructeur de Simulation
     */
    public Simulation() {
        environnement = new KitC();
    }

    /**
     * Fonction de lancement de la simulation
     * @param nbEtapes Nombre d'étape dans la simulation
     * @param nbServices Nombre de guichet dans le monde
     * @param nbClients Nombre de client dans le monde
     * @param tabJetonsServices Tableau des jetons dans le monde
     * @return Un tableau de INT
     */
    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    /**
     * Fonction de repérage des clients
     * @param nbEtapes Nombre d'étape dans
     * @param nbClients Nombre de client dans le monde
     * @return Un tableau de int
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * Permet de nettoyer le code
     */
    public native void nettoyage();

    /**
     * Fonction de simulation regroupant les fonctions précédente
     * @param monde Le monde dans lequel la simulation doit s'executer
     */
    public void simuler(Monde monde){
        getEnvironnement().creerFichier(monde.toC().toString());
        getEnvironnement().compiler();
        getEnvironnement().construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk.so");

        // On mets les jetons dans un tableau
        int[] tabJetonsGuichet = new int[monde.nbGuichets()];
        for (Etape etape : monde) {
            if (etape.estUnGuichet()) {
                Guichet guichet = (Guichet) etape;
                tabJetonsGuichet[guichet.getSemaphore() - 1] = guichet.getNbJetons();
            }
        }

        // On lance la simulation.
        int[] processus = start_simulation(monde.nbEtapes(), monde.nbGuichets(), getNbClients(), tabJetonsGuichet);

        // On affiche les clients.
        System.out.print("\nLes clients : ");
        for (int i = 0; i < getNbClients(); ++i) {
            System.out.print(processus[i] + " ");
        }

        int[] clients;
        for (int k = 0; k < 20; k++) {

            System.out.print("\n");
            clients = ou_sont_les_clients(monde.nbEtapes(), getNbClients());
            try{
                Thread.sleep(500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            // On parcourt les étapes.
            for (int j = 0; j < monde.nbEtapes(); ++j) {
                int decalage = clients[(j * (getNbClients() + 1))];
                System.out.print("Etape : " + monde.getEtape(j).getNom() + " " + monde.getEtape(j).getNum() + " - " + decalage + " client(s) ➡ ");
                for (int i = 0; i < decalage; ++i) {
                    System.out.print(decalage + 1 + i + " ");
                }
                System.out.print("\n");
            }
        }
        nettoyage();
    }

    /**
     * Getter de l'environnement
     * @return L'environnement soit un KitC
     */
    public KitC getEnvironnement() {
        return environnement;
    }

    /**
     * Getter du nombre de client
     * @return Le nombre de client en int
     */
    public int getNbClients() {
        return nbClients;
    }

    /**
     * Setter du nombre de client
     * @param nbClients Nombre de client à mettre dans le monde
     */
    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

}

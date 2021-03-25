package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {

    KitC environnement;
    int nbClients;

    public Simulation() {
        environnement = new KitC();
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    public native void nettoyage();

    public void simuler(Monde monde) throws InterruptedException {
        environnement.creerFichier(monde.toC().toString());
        environnement.compiler();
        environnement.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk.so");

        // On mets les jetons dans un tableau
        int[] tabJetonsGuichet = new int[monde.nbGuichets()];
        for (Etape etape : monde) {
            if (etape.estUnGuichet()) {
                Guichet guichet = (Guichet) etape;
                tabJetonsGuichet[guichet.getSemaphore()-1] = guichet.getNbJetons();
            }
        }

        for (int value : tabJetonsGuichet) {
            System.out.println(value);
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
            Thread.sleep(1000);

            // On parcourt les étapes.
            for (int j = 0; j < monde.nbEtapes(); ++j) {
                System.out.print("Etape : " + monde.getEtape(j).getNom() + " " + monde.getEtape(j).getNum() + " - " + clients[(j * (getNbClients() + 1))] + " client(s) ➡ ");
                for (int i = 0; i < clients[(j * (getNbClients() + 1))]; ++i) {
                    System.out.print(clients[(j * (getNbClients() + 1)) + 1 + i] + " ");
                }
                System.out.print("\n");
            }
        }
        nettoyage();
    }
}

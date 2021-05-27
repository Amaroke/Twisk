package twisk.simulation;

import javafx.concurrent.Task;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.mondeIG.SujetObserve;
import twisk.outils.GestionnaireThreads;
import twisk.outils.KitC;
import twisk.vues.Observateur;

import java.util.Iterator;

/**
 * Classe Simulation
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */
@SuppressWarnings({"BusyWait", "unused"})
public class Simulation extends SujetObserve implements Iterable<Client> {

    private GestionnaireClients gestionnaireClients;
    private final KitC environnement;
    private int nbClients;
    private boolean simulationDebute;

    /**
     * Constructeur de Simulation.
     */
    public Simulation() {
        environnement = new KitC();
        simulationDebute = false;
    }

    /**
     * Fonction de lancement de la simulation.
     *
     * @param nbEtapes          Nombre d'étape dans la simulation
     * @param nbServices        Nombre de guichet dans le monde
     * @param nbClients         Nombre de client dans le monde
     * @param tabJetonsServices Tableau des jetons dans le monde
     * @return Un tableau de INT
     */
    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    /**
     * Fonction de repérage des clients.
     *
     * @param nbEtapes  Nombre d'étape dans
     * @param nbClients Nombre de client dans le monde
     * @return Un tableau de int
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * Permet de nettoyer le code.
     */
    public native void nettoyage();

    /**
     * Fonction de simulation regroupant les fonctions précédente.
     *
     * @param monde Le monde dans lequel la simulation doit s'executer
     */
    public void simuler(Monde monde) {

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    simulationDebute = true;
                    System.out.println(monde.toString());
                    getEnvironnement().creerFichier(monde.toC().toString());
                    getEnvironnement().compiler();
                    getEnvironnement().construireLaLibrairie();
                    System.load("/tmp/twisk/libTwisk" + environnement.getNumLib() + ".so");

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
                    System.out.print("Les clients : ");
                    int[] clientsPID = new int[getNbClients()];
                    for (int i = 0; i < getNbClients(); ++i) {
                        System.out.print(processus[i] + " ");
                        clientsPID[i] = processus[i];
                    }
                    gestionnaireClients.setClients(clientsPID);

                    int[] clients;
                    clients = ou_sont_les_clients(monde.nbEtapes(), getNbClients());
                    // On regarde si tous les clients sont dans le sasSortie.
                    while (clients[((getNbClients() + 1))] != getNbClients()) {
                        System.out.print("\n");
                        clients = ou_sont_les_clients(monde.nbEtapes(), getNbClients());
                        Thread.sleep(1000);
                        // On parcourt les étapes.
                        for (int j = 0; j < monde.nbEtapes(); ++j) {
                            int decalage = clients[(j * (getNbClients() + 1))];
                            System.out.print("Etape : " + monde.getEtape(j).getNom() + " " + monde.getEtape(j).getNum() + " - " + decalage + " client(s) ➡ ");
                            for (int i = 0; i < decalage; ++i) {
                                gestionnaireClients.allerA(clients[(j * (getNbClients() + 1)) + 1 + i], monde.getEtape(j), i);
                                System.out.print(clients[(j * (getNbClients() + 1)) + 1 + i] + " ");
                            }
                            System.out.print("\n");
                        }
                        notifierObservateur();
                    }
                    GestionnaireThreads.getInstance().detruireTout();
                    simulationDebute = false;
                    nettoyage();
                    notifierObservateur();
                } catch (InterruptedException e) {
                    // Le thread se termine.
                    GestionnaireThreads.getInstance().detruireTout();
                    simulationDebute = false;
                    notifierObservateur();
                    for (Client c : gestionnaireClients.getListeClient()) {
                        environnement.killPid(c.getNumeroClient());
                    }
                    nettoyage();
                    System.out.println("Destruction des threads et processus C terminé.");
                }
                return null;
            }
        };
        GestionnaireThreads.getInstance().lancer(task);
    }

    /**
     * Getter de l'environnement.
     *
     * @return L'environnement soit un KitC
     */
    public KitC getEnvironnement() {
        return environnement;
    }

    /**
     * Getter du nombre de client.
     *
     * @return Le nombre de client en int
     */
    public int getNbClients() {
        return nbClients;
    }

    /**
     * Setter du nombre de client.
     *
     * @param nbClients Nombre de client à mettre dans le monde
     */
    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
        gestionnaireClients = new GestionnaireClients(this.nbClients);
    }

    public GestionnaireClients getGestionnaireClients() {
        return gestionnaireClients;
    }



    /**
     * Fonction iterator simulation.
     *
     * @return Iterator<Client>
     */
    @Override
    public Iterator<Client> iterator() {
        return gestionnaireClients.iterator();
    }

    @Override
    public void ajouterObservateur(Observateur o) {
        super.ajouterObservateur(o);
    }

    public boolean isSimulationDebute() {
        return simulationDebute;
    }

    public void setSimulationDebute(boolean simulationDebute) {
        this.simulationDebute = simulationDebute;
    }
}

package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe GestionnaireClients
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class GestionnaireClients implements Iterable<Client> {

    private final ArrayList<Client> listeClient;

    /**
     * Constructeur GestionnaireClients
     *
     * @param nbClients int
     */
    public GestionnaireClients(int nbClients){
        listeClient = new ArrayList<>(nbClients);
    }

    /**
     * Setter clients
     *
     * @param tabClients int ...
     */
    public void setClients(int ... tabClients){
        for(int client : tabClients){
            listeClient.add(new Client(client));
        }
    }

    /**
     * Fonction allerA
     *
     * @param numeroClient int
     * @param etape Etape
     * @param rang int
     */
    public void allerA(int numeroClient, Etape etape, int rang){
        for(Client client: listeClient){
            if(client.getNumeroClient() == numeroClient){
                client.allerA(etape,rang);
            }
        }
    }

    /**
     * Fonction de reset.
     */
    void reset(){
        listeClient.clear();
    }

    /**
     * Fonction itérator
     *
     * @return Iterator<Client>
     */
    @Override
    public Iterator<Client> iterator() {
        return listeClient.iterator();
    }

    /**
     * Getter listeclient
     *
     * @return ArrayList<Client>
     */
    public ArrayList<Client> getListeClient() {
        return listeClient;
    }
}

package twisk.simulation;

import twisk.monde.Etape;

/**
 * Classe Client
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */


public class Client {
    private int numeroClient;
    private int rang;
    private Etape etape;


    /**
     * Constructeur Client
     *
     * @param numero int
     */
    public Client(int numero){
        this.numeroClient = numero;
    }

    /**
     * Fonction allerA
     *
     * @param etape Etape
     * @param rang int
     */
    public void allerA(Etape etape, int rang){
        this.etape = etape;
        this.rang = rang;
    }


    /**
     * Getter Numero Client
     * @return numeroClient
     */
    public int getNumeroClient() {
        return numeroClient;
    }


    /**
     * Getter Rang
     *
     * @return rang
     */
    public int getRang() {
        return rang;
    }

    /**
     * Getter etape.
     *
     * @return etape.
     */
    public Etape getEtape() {
        return etape;
    }
}

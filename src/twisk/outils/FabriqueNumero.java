package twisk.outils;

/**
 * Classe FabriqueNumero
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class FabriqueNumero {

    private static final FabriqueNumero instance = new FabriqueNumero();
    private int cptEtape;
    private int cptSemaphore;
    private int numlib;

    /**
     * Constructeur de FabriqueNumero.
     */
    public FabriqueNumero() {
        cptEtape = 0;
        cptSemaphore = 1;
        numlib = 0;
    }

    /**
     * Getter de l'instance de FabriqueNumero.
     *
     * @return L'instance de FabriqueNumero
     */
    public static FabriqueNumero getInstance() {
        return instance;
    }

    /**
     * Getter du numero d'étapes.
     *
     * @return Le numéro de l'étape actuelle
     */
    public int getNumeroEtape() {
        cptEtape++;
        return cptEtape - 1;
    }

    /**
     * Getter d'un numéro de la lib pour le classloader.
     *
     * @return int
     */
    public int getNumLib() {
        numlib++;
        return numlib - 1;
    }

    /**
     * Getter sémaphore.
     *
     * @return Le numéro du guichet actuelle
     */
    public int getNumeroSemaphore() {
        cptSemaphore++;
        return cptSemaphore - 1;
    }

    /**
     * Reset de du nombre d'étapes et de sémaphore.
     */
    public void reset() {
        cptEtape = 0;
        cptSemaphore = 1;
    }
}
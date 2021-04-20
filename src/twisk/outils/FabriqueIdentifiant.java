package twisk.outils;

/**
 * Classe FabriqueIdentifiant.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class FabriqueIdentifiant {
    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noEtape;

    /**
     * Constructeur de la fabrique d'identifiant.
     */
    private FabriqueIdentifiant() {
        this.noEtape = 0;
    }

    /**
     * Getter de l'instance de la fabrique.
     *
     * @return FabriqueIdentifiant
     */
    public static FabriqueIdentifiant getInstance() {
        return instance;
    }

    /**
     * Getter IdentifiantEtape ( son numéro en string ).
     *
     * @return String
     */
    public String getIdentifiantEtape() {
        noEtape++;
        return String.valueOf(noEtape - 1);
    }

}

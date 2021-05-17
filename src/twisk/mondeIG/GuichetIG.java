package twisk.mondeIG;

/**
 * Classe EcouteurDeselection.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class GuichetIG extends EtapeIG {

    private int nbJetons;

    /**
     * Constructeur ActiviteIG.
     *
     * @param nom  String
     * @param idf  String
     * @param larg int
     * @param haut int
     */
    public GuichetIG(String nom, String idf, int larg, int haut, int nbJetons) {
        super(nom, idf, larg, haut);
        this.nbJetons = nbJetons;
    }

    /**
     * Getter nombre de jeton.
     *
     * @return int
     */
    public int getNbJetons() {
        return nbJetons;
    }

    /**
     * Setter nombre de jeton
     *
     * @param nbJetons int
     */
    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    /**
     * Fonction permettant de savoir si l'étpae est un guichet.
     *
     */
    @Override
    public boolean estUnGuichet() {
        return true;
    }
}

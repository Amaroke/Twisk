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

    public int getNbJetons() {
        return nbJetons;
    }

    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }
}

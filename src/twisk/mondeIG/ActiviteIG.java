package twisk.mondeIG;

/**
 * Classe EcouteurDeselection.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class ActiviteIG extends EtapeIG {

    private int temps;
    private int ecartTemps;

    /**
     * Constructeur ActiviteIG.
     *
     * @param nom  String
     * @param idf  String
     * @param larg int
     * @param haut int
     */
    public ActiviteIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    /**
     * Getter temps.
     *
     * @return int
     */
    public int getTemps() {
        return this.temps;
    }

    /**
     * Setter Temps.
     *
     * @param temps int
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * Getter ecart temps
     *
     * @return int
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * Setter ecartemps.
     *
     * @param ecartTemps int
     */
    public void setEcartTemps(int ecartTemps) {
        this.ecartTemps = ecartTemps;
    }
}

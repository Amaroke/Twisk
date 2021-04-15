package twisk.monde;

/**
 * Classe Activite
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class Activite extends Etape {

    private int temps = 1;
    private int ecartTemps = 1;

    /**
     * Constructeur d'activite avec son nom.
     *
     * @param nom Nom de l'activité
     */
    public Activite(String nom) {
        super(nom);
    }

    /**
     * Constructeur d'activite avec son nom temps et écart temps.
     *
     * @param nom Nom de l'activité
     * @param t   Temps d'attente dans l'activité
     * @param e   EcartTemps de l'activité
     */
    public Activite(String nom, int t, int e) {
        super(nom);
        this.temps = t;
        this.ecartTemps = e;
    }

    /**
     * Getter estUnActivite.
     *
     * @return Vrai
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }

    /**
     * Fonction transformation en code C.
     *
     * @return Un string du codeC de l'activite
     */
    @Override
    public String toC() {
        return "delai(" + getTemps() + "," + getEcartTemps() + ");\n" +
                "transfert(" + getNum() + "," + getSuivant().getNum() + ");\n" + getSuivant().toC();
    }

    /**
     * Fonction de mise en string de l'activité.
     *
     * @return Un string contenant le numero et le nom de l'activité
     */
    @Override
    public String toString() {
        return "Étape N°" + getNum() + " " + getNom();
    }

    /**
     * Getter temps.
     *
     * @return Le temps de l'activité
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Getter ecartTemps.
     *
     * @return L'écart temps de l'activité
     */
    public int getEcartTemps() {
        return ecartTemps;
    }
}

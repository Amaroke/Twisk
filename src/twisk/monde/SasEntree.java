package twisk.monde;

/**
 * Classe SasEntree
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class SasEntree extends Activite {

    /**
     * Constructeur du sasEntree
     */
    public SasEntree() {
        super("SasEntree");
    }

    /**
     * Fonction de mise en string de sasEntree.
     *
     * @return Un string du code C de sasEntree
     */
    public String toC() {
        return "entrer(" + getNum() + "); \n" +
                "delai(" + 3 + "," + 1 + "); \n" +
                "transfert(" + getNum() + "," + getSuivant().getNum() + ");\n" + getSuivant().toC();
    }
}

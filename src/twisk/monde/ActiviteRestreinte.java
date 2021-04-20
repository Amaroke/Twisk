package twisk.monde;

/**
 * Classe Activite
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class ActiviteRestreinte extends Activite {

    /**
     * Constructeur ActiviteRestreinte avec nom.
     *
     * @param nom Nom de l'activité restreinte
     */
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    /**
     * Constructeur ActiviteRestreinte avec nom temps et ecart temps.
     *
     * @param nom Nom de l'activité restreinte
     * @param temps   Temps de l'activité restreinte
     * @param ecartTemps   EcartTemps de l'activité restreinte
     */
    public ActiviteRestreinte(String nom, int temps, int ecartTemps) {
        super(nom, temps, ecartTemps);
    }

    @Override
    public String toC() {
        return "transfert(" + getNum() + "," + getSuivant().getNum() + ");\n" + getSuivant().toC();
    }
}

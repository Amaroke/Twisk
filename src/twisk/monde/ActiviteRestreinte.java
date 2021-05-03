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

    /**
     * Fonction de mise en C pour l'executions du monde
     *
     * @return string
     */
    @Override
    public String toC() {
        StringBuilder strC = new StringBuilder();
        strC.append("delai(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("int nb = (int)((rand()/(float) RAND_MAX)*").append(getGestionnaireSuccesseurs().nbEtapes()).append(");\n").append("switch(nb) {\n");
        for (int i = 0; i < getGestionnaireSuccesseurs().nbEtapes(); ++i) {
            strC.append("case ").append(i).append(": {").append("transfert(").append(getNom().replaceAll("\\s+", "")).append(",").append(getGestionnaireSuccesseurs().getEtapes().get(i).getNom().replaceAll("\\s+", "")).append(");\n").append(getGestionnaireSuccesseurs().getEtapes().get(i).toC()).append("break;\n}\n");
        }
        strC.append("}");
        return strC.toString();
    }
}

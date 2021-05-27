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
        StringBuilder strC = new StringBuilder();
        strC.append("entrer(").append(getNom().replaceAll("\\s+", "")).append("); \n").append("delai(").append(3).append(",").append(1).append("); \n");
        strC.append("delai(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("int nb = (int)((rand()/(float) RAND_MAX)*").append(getGestionnaireSuccesseurs().nbEtapes()).append(");\n").append("switch(nb) {\n");
        for(int i = 0; i < getGestionnaireSuccesseurs().nbEtapes(); ++i) {
            strC.append("case ").append(i).append(": {").append("transfert(").append(getNom().replaceAll("\\s+", "")).append(",").append(getGestionnaireSuccesseurs().getEtapes().get(i).getNom().replaceAll("\\s+", "")).append(");\n").append(getGestionnaireSuccesseurs().getEtapes().get(i).toC()).append("break;\n}\n");
        }
        strC.append("}");
        return strC.toString();
    }
}

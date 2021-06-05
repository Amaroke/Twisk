package twisk.monde;

/**
 * Classe SasEntree
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class SasEntree extends Activite {
    private final int loi;

    /**
     * Constructeur du sasEntree
     */
    public SasEntree(int loi) {
        super("SasEntree");
        this.loi = loi;
    }

    /**
     * Fonction de mise en string de sasEntree.
     *
     * @return Un string du code C de sasEntree
     */
    public String toC() {
        StringBuilder strC = new StringBuilder();
        strC.append("entrer(").append(getNom().replaceAll("\\s+", "")).append("); \n");
        switch (loi) {
            case 1:
                strC.append("delai((int) delaiUniforme(5,1),1); \n");
                break;
            case 2:
                strC.append("delai((int) delaiGauss(5,1),1); \n");
                break;
            case 3:
                strC.append("delai((int) delaiExponentiel(1.0/5.0),1); \n");
                break;
        }
        strC.append("int nb = (int)((rand()/(float) RAND_MAX)*").append(getGestionnaireSuccesseurs().nbEtapes()).append(");\n").append("switch(nb) {\n");
        for (int i = 0; i < getGestionnaireSuccesseurs().nbEtapes(); ++i) {
            strC.append("case ").append(i).append(": {").append("transfert(").append(getNom().replaceAll("\\s+", "")).append(",").append(getGestionnaireSuccesseurs().getEtapes().get(i).getNom().replaceAll("\\s+", "")).append(");\n").append(getGestionnaireSuccesseurs().getEtapes().get(i).toC()).append("break;\n}\n");
        }
        strC.append("}");
        return strC.toString();
    }
}

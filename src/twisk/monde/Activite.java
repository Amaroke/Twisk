package twisk.monde;

import java.io.Serializable;

/**
 * Classe Activite
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class Activite extends Etape implements Serializable {

    private final int temps;
    private final int ecartTemps;

    /**
     * Constructeur d'activite avec son nom.
     *
     * @param nom Nom de l'activité
     */
    public Activite(String nom) {
        super(nom);
        this.temps = 3; // Temps par défaut de 3.
        this.ecartTemps = 1; // Ecart temps par défaut de 1.
    }

    /**
     * Constructeur d'activite avec sodeserialization();n nom temps et écart temps.
     *
     * @param nom        Nom de l'activité
     * @param temps      Temps d'attente dans l'activité
     * @param ecartTemps EcartTemps de l'activité
     */
    public Activite(String nom, int temps, int ecartTemps) {
        super(nom);
        this.temps = temps;
        this.ecartTemps = ecartTemps;
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
        StringBuilder strC = new StringBuilder();
        strC.append("delai(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("int nb = (int)((rand()/(float) RAND_MAX)*").append(getGestionnaireSuccesseurs().nbEtapes()).append(");\n").append("switch(nb) {\n");
                for(int i = 0; i < getGestionnaireSuccesseurs().nbEtapes(); ++i) {
                    strC.append("case ").append(i).append(": {").append("transfert(").append(getNom().replaceAll("\\s+", "")).append(",").append(getGestionnaireSuccesseurs().getEtapes().get(i).getNom().replaceAll("\\s+", "")).append(");\n").append(getGestionnaireSuccesseurs().getEtapes().get(i).toC()).append("break;\n}\n");
                }
                strC.append("}");
        return strC.toString();
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

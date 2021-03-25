package twisk.monde;

public class ActiviteRestreinte extends Activite {

    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    @Override
    public String toC() {
        return "transfert(" + getNum() + "," + getGestionnaireSuccesseurs().getEtapes().get(0).getNum() + ");\n" + getGestionnaireSuccesseurs().getEtapes().get(0).toC();
    }

    public int getTemps() {
        return temps;
    }

    public int getEcartTemps() {
        return ecartTemps;
    }
}

package twisk.monde;

public class Activite extends Etape {

    public int temps = 1;
    int ecartTemps = 1;

    public Activite(String nom) {
        super(nom);
    }

    public Activite(String nom, int t, int e) {
        super(nom);
        this.temps = t;
        this.ecartTemps = e;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toC() {
        return "delai(" + getTemps() + "," + getEcartTemps() + ");\n" +
                "transfert(" + getNum() + "," + getSuivant().getNum() + ");\n" + getSuivant().toC();
    }

    @Override
    public String toString() {
        return "Étape N°" + getNum() + " " + getNom();
    }

    public int getTemps() {
        return temps;
    }

    public int getEcartTemps() {
        return ecartTemps;
    }
}

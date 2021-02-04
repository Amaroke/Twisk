package twisk.monde;

public class Activite extends Etape {

    public int temps;
    int ecartTemps;

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
    public String toString() {
        return "Étape N°" + getNum() + " " + getNom();
    }

}

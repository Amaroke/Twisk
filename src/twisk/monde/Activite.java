package twisk.monde;

public class Activite extends Etape {
    public int temps;
    int ecartTemps;

    public Activite(String nom) {
        super(nom);
    }

    public Activite(String nom, int t, int e) {
        super(nom);
    }

    public boolean estUneActivite() {
        return true;
    }

}

package twisk.monde;

public class Guichet extends Etape {
    int nbJetons;

    public Guichet(String nom) {
        super(nom);
    }

    public Guichet(String nom, int nb){
        super(nom);
    }

    public boolean estUnGuichet(){
        return true;
    }
}

package twisk.monde;

import java.util.Iterator;

public abstract class Etape implements Iterable{
    public String nom;

    public Etape(String nom){ }

    void ajouterSuccesseur(Etape... e){ }

    boolean estUneActive(){
        return true;
    }

    boolean estUnGuichet(){
        return true;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}

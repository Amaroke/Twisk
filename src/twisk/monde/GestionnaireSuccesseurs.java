package twisk.monde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable{

    ArrayList<Etape> etapes;

    public GestionnaireSuccesseurs(){
        etapes  = new ArrayList<>();
    }

    public void ajouter(Etape... etapes){
        Collections.addAll(this.etapes, etapes);
    }

    int nbEtapes(){
        return etapes.size();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}

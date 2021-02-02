package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class GestionnaireEtapes implements Iterable {

    ArrayList<Etape> etapes;

    public GestionnaireEtapes(){
        etapes = new ArrayList<>();
    }

    void ajouter(Etape... etapes){
        Collections.addAll(this.etapes,etapes);
    }

    int nbEtapes(){
        return etapes.size();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}

package twisk.monde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GestionnaireEtapes implements Iterable<Etape> {

    ArrayList<Etape> etapes;


    public GestionnaireEtapes() {
        etapes = new ArrayList<>();
    }

    void ajouter(Etape... etapes) {
        Collections.addAll(this.etapes, etapes);
    }

    int nbEtapes() {
        return etapes.size();
    }

    @Override
    public Iterator<Etape> iterator() {
        return etapes.iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Etape etape : etapes) {
            s.append(etape).append(" : ").append(etape.nbSuccesseurs()).append(" successeur(s) - ").append(etape.getSuccesseursString()).append("\n");
        }
        return s.toString();
    }
}

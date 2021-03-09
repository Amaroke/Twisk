package twisk.monde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape> {

    ArrayList<Etape> etapes;

    public GestionnaireSuccesseurs() {
        etapes = new ArrayList<>();
    }

    public void ajouter(Etape... etapes) {
        Collections.addAll(this.etapes, etapes);
    }

    int nbEtapes() {
        return etapes.size();
    }

    public ArrayList<Etape> getEtapes() {
        return etapes;
    }

    @Override
    public Iterator<Etape> iterator() {
        return etapes.iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Etape etape : etapes) {
            s.append(etape).append(" ");
        }
        return s.toString();
    }
}

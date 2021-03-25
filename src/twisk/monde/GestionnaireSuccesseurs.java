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
        Collections.addAll(getEtapes(), etapes);
    }

    int nbEtapes() {
        return getEtapes().size();
    }

    @Override
    public Iterator<Etape> iterator() {
        return getEtapes().iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Etape etape : getEtapes()) {
            s.append(etape).append(" ");
        }
        return s.toString();
    }

    public ArrayList<Etape> getEtapes() {
        return etapes;
    }
}

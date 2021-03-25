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
        Collections.addAll(this.getEtapes(), etapes);
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
            s.append(etape).append(" : ").append(etape.nbSuccesseurs()).append(" successeur(s) - ").append(etape.getSuccesseursString()).append("\n");
        }
        return s.toString();
    }

    public ArrayList<Etape> getEtapes() {
        return etapes;
    }
}

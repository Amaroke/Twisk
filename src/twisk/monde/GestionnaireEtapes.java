package twisk.monde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Classe GestionnaireEtapes
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class GestionnaireEtapes implements Iterable<Etape> {

    private ArrayList<Etape> etapes;

    /**
     * Constructeur du gestionnaire d'étape.
     */
    public GestionnaireEtapes() {
        etapes = new ArrayList<>();
    }

    /**
     * Ajoute la collection d'étapes.
     *
     * @param etapes Liste d'etape
     */
    void ajouter(Etape... etapes) {
        Collections.addAll(this.getEtapes(), etapes);
    }

    /**
     * Getteur du nombre d'étapes.
     *
     * @return Le nombre d'étape
     */
    int nbEtapes() {
        return getEtapes().size();
    }

    /**
     * Iterator d'étape.
     *
     * @return L'itérateur d'étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return getEtapes().iterator();
    }

    /**
     * Fonction toString.
     *
     * @return Un chaîne de caractère contenant le nombre de successeurs et les successeurs
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Etape etape : getEtapes()) {
            s.append(etape).append(" : ").append(etape.nbSuccesseurs()).append(" successeur(s) - ").append(etape.getSuccesseursString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Getter d'étape.
     *
     * @return L'arrayList d'étapes
     */
    public ArrayList<Etape> getEtapes() {
        return etapes;
    }
}

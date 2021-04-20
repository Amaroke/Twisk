package twisk.monde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Classe GestionnaireSuccesseurs
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class GestionnaireSuccesseurs implements Iterable<Etape> {

    private final ArrayList<Etape> etapes;

    /**
     * Constructeur de GestionnaireSuccesseurs.
     */
    public GestionnaireSuccesseurs() {
        etapes = new ArrayList<>();
    }

    /**
     * Ajout de successeurs.
     *
     * @param etapes Collection d'étape
     */
    public void ajouter(Etape... etapes) {
        Collections.addAll(getEtapes(), etapes);
    }

    /**
     * Getter du nombre d'étapes.
     *
     * @return Le nombre d'étape dans le gestionnaireSuccesseurs
     */
    int nbEtapes() {
        return getEtapes().size();
    }

    /**
     * Iterateur d'étape.
     *
     * @return Un itérateur d'étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return getEtapes().iterator();
    }

    /**
     * Fonction mise en string.
     *
     * @return Un string de GestionnaireSuccesseurs
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Etape etape : getEtapes()) {
            s.append(etape).append(" ");
        }
        return s.toString();
    }

    /**
     * Getter des étapes.
     *
     * @return L'arraylist d'étapes
     */
    public ArrayList<Etape> getEtapes() {
        return etapes;
    }
}

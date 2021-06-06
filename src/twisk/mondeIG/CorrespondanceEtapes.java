package twisk.mondeIG;

import twisk.monde.Etape;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe CorrespondanceEtapes
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class CorrespondanceEtapes implements Serializable {

    private final HashMap<EtapeIG, Etape> corresp;

    /**
     * Constructeur correspondanceEtape
     */
    public CorrespondanceEtapes() {
        corresp = new HashMap<>(10);
    }

    /**
     * Fonction d'ajout à la Hashmap de correspondance etape.
     * @param etig EtapeIG
     * @param et Etape
     */
    public void ajouter(EtapeIG etig, Etape et) {
        corresp.put(etig, et);
    }

    /**
     * Getter de l'étape correspondante
     * @param e EtapeIG
     * @return Etape
     */
    public Etape get(EtapeIG e) {
        return corresp.get(e);
    }

}

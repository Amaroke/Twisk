package twisk.mondeIG;

import twisk.vues.Observateur;

import java.util.ArrayList;

/**
 * Classe SujetObservé.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class SujetObserve {

    private final ArrayList<Observateur> obs = new ArrayList<>(10);

    /**
     * Fonction ajout d'observateur.
     *
     * @param o Observateur
     */
    public void ajouterObservateur(Observateur o) {
        this.obs.add(o);
    }

    /**
     * Fonction notification d'observateur.
     */
    public void notifierObservateur() {
        for (Observateur o : this.obs) o.reagir();
    }
}

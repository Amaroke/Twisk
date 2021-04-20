package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurSupprimer.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurSupprimer implements EventHandler<ActionEvent> {
    private final MondeIG monde;

    /**
     * Constructeur EcouteurSupprimer.
     *
     * @param m MondeIG
     */
    public EcouteurSupprimer(MondeIG m) {
        this.monde = m;
    }

    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        monde.gestionSuppresion();
    }
}

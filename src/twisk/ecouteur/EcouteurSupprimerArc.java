package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurSupprimerArc.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurSupprimerArc implements EventHandler<ActionEvent> {
    private final MondeIG monde;

    /**
     * Constructeur EcouteurSupprimerArc.
     *
     * @param m MondeIG
     */
    public EcouteurSupprimerArc(MondeIG m) {
        this.monde = m;
    }


    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        monde.gestionSuppressionArc();
    }
}

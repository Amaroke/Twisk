package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurEntree.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurEntree implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Constructeur EcouteurEntre
     *
     * @param m MondeIG
     */
    public EcouteurEntree(MondeIG m) {
        this.monde = m;
    }


    /**
     * Fonction handle
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        monde.gestionEntre();
    }
}

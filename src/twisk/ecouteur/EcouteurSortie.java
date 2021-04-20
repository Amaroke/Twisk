package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurSortie.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurSortie implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Constructeur EcouteurSortie.
     *
     * @param m MondeIG
     */
    public EcouteurSortie(MondeIG m) {
        this.monde = m;
    }


    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        monde.gestionSortie();
    }
}

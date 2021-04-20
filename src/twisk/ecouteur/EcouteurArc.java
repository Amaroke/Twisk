package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurArc.
 *
 * @author Lambert Calvin
 * @version 1.0
 */


public class EcouteurArc implements EventHandler<MouseEvent> {
    private final MondeIG monde;
    private final ArcIG arc;

    /**
     * Constructeur EcouteurArc.
     *
     * @param m   MondeIG
     * @param arc ArcIG
     */
    public EcouteurArc(MondeIG m, ArcIG arc) {
        this.monde = m;
        this.arc = arc;
    }


    /**
     * Fonction handle ecouteur.
     *
     * @param event MouseEvent
     */
    @Override
    public void handle(MouseEvent event) {
        monde.gestionArc(arc);
    }
}

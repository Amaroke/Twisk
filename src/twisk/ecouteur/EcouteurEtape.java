package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurEtape.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurEtape implements EventHandler<MouseEvent> {
    private final MondeIG monde;
    private final EtapeIG etape;

    /**
     * Constructeur EcouteurEtape.
     *
     * @param e EtapeIG
     * @param m MondeIG
     */
    public EcouteurEtape(EtapeIG e, MondeIG m) {
        etape = e;
        monde = m;
    }

    /**
     * Fonction handle.
     *
     * @param event MouseEvent
     */
    @Override
    public void handle(MouseEvent event) {
        monde.gestionEtape(etape);
    }
}

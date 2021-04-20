package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurEtape.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurEtape implements EventHandler<MouseEvent> {

    private final MondeIG monde;
    private final EtapeIG etape;

    /**
     * Constructeur EcouteurEtape.
     *
     * @param etape EtapeIG
     * @param monde MondeIG
     */
    public EcouteurEtape(EtapeIG etape, MondeIG monde) {
        this.etape = etape;
        this.monde = monde;
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

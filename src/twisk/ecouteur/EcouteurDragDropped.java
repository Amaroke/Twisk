package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;
import twisk.vues.VueMondeIG;

/**
 * Classe EcouteurDragDropped.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurDragDropped implements EventHandler<DragEvent> {
    private final MondeIG monde;
    private final VueMondeIG vueMondeIG;

    /**
     * Constructeur EcouteurDragDropped.
     *
     * @param m MondeIG
     * @param v VueMondeIG
     */
    public EcouteurDragDropped(MondeIG m, VueMondeIG v) {
        this.monde = m;
        this.vueMondeIG = v;
    }


    /**
     * Fonction handle.
     *
     * @param dragEvent DragEvent
     */
    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            String nodeId = db.getString();
            VueEtapeIG vueetape = (VueEtapeIG) vueMondeIG.lookup("#" + nodeId);
            if (vueetape != null) {
                vueetape.getEtape().setPosX((int) dragEvent.getX());
                vueetape.getEtape().setPosY((int) dragEvent.getY());
                vueetape.getEtape().setPDC();
                vueetape.relocate((int) dragEvent.getX(), (int) dragEvent.getY());
                success = true;
            }
        }
        dragEvent.setDropCompleted(success);
        monde.notifierObservateur();
        dragEvent.consume();
    }
}

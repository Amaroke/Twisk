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
 * @author & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurDragDropped implements EventHandler<DragEvent> {

    private final MondeIG monde;
    private final VueMondeIG vueMondeIG;

    /**
     * Constructeur EcouteurDragDropped.
     *
     * @param monde MondeIG
     * @param vue   VueMondeIG
     */
    public EcouteurDragDropped(MondeIG monde, VueMondeIG vue) {
        this.monde = monde;
        this.vueMondeIG = vue;
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
            VueEtapeIG vueEtape = (VueEtapeIG) vueMondeIG.lookup("#" + nodeId);
            if (vueEtape != null) {
                vueEtape.getEtape().setPosX((int) dragEvent.getX());
                vueEtape.getEtape().setPosY((int) dragEvent.getY());
                if(!vueEtape.getEtape().estUnGuichet()) {
                    vueEtape.getEtape().setPDC();
                } else {
                    vueEtape.getEtape().setPDCGuichet();
                }
                vueEtape.relocate((int) dragEvent.getX(), (int) dragEvent.getY());
                success = true;
            }
        }
        dragEvent.setDropCompleted(success);
        monde.notifierObservateur();
        dragEvent.consume();
    }
}

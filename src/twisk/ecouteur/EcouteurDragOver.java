package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

/**
 * Classe EcouteurDragOver.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurDragOver implements EventHandler<DragEvent> {

    /**
     * Fonction handle.
     *
     * @param dragEvent DragEvent
     */
    @Override
    public void handle(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasString()) {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }
        dragEvent.consume();
    }
}

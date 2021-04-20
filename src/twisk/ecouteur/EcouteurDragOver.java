package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

/**
 * Classe EcouteurDragOver.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurDragOver implements EventHandler<DragEvent> {

    public EcouteurDragOver() {
    }

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

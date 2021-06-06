package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import twisk.vues.VueEtapeIG;

/**
 * Classe EcouteurDetected.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurDragDetected implements EventHandler<MouseEvent> {

    private final VueEtapeIG Vetape;

    /**
     * Constructeur EcouteurDragDetected.
     *
     * @param e VueEtapeIG
     */
    public EcouteurDragDetected(VueEtapeIG e) {
        Vetape = e;
    }

    /**
     * Fonction handle.
     *
     * @param event MouseEvent
     */
    @Override
    public void handle(MouseEvent event) {
        Dragboard db = Vetape.startDragAndDrop(TransferMode.MOVE);
        WritableImage snapshot = Vetape.snapshot(new SnapshotParameters(), null);
        db.setDragView(snapshot);
        ClipboardContent content = new ClipboardContent();
        content.putString(Vetape.getId());
        db.setContent(content);
        event.consume();
    }
}

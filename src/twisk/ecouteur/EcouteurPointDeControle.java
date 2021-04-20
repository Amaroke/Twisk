package twisk.ecouteur;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.exceptions.TwiskException.AlreadyExistException;
import twisk.exceptions.TwiskException.SamePointException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * Classe EcouteurPointDeControle.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurPointDeControle implements EventHandler<MouseEvent> {
    private final PointDeControleIG p;
    private final MondeIG m;


    /**
     * Constructeur EcouteurPointDeControle.
     *
     * @param point PointDeControleIG
     * @param monde MondeIG
     */
    public EcouteurPointDeControle(PointDeControleIG point, MondeIG monde) {
        m = monde;
        p = point;
    }


    /**
     * Fonction handle.
     *
     * @param mouseEvent Mouse event
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            m.creerArc(p);
        } catch (SamePointException | AlreadyExistException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText(e.getMessage());
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(4));
            p.setOnFinished(event -> a.close());
            p.play();
        }
    }
}

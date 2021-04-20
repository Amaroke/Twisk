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
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurPointDeControle implements EventHandler<MouseEvent> {

    private final PointDeControleIG pointDeControle;
    private final MondeIG monde;

    /**
     * Constructeur EcouteurPointDeControle.
     *
     * @param point PointDeControleIG
     * @param monde MondeIG
     */
    public EcouteurPointDeControle(PointDeControleIG point, MondeIG monde) {
        this.monde = monde;
        this.pointDeControle = point;
    }

    /**
     * Fonction handle.
     *
     * @param mouseEvent Mouse event
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            monde.creerArc(pointDeControle);
        } catch (SamePointException | AlreadyExistException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setContentText(e.getMessage());
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(4));
            p.setOnFinished(event -> a.close());
            p.play();
        }
    }
}

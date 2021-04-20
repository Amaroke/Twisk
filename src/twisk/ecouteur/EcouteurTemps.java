package twisk.ecouteur;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.TwiskException.TempsIncorrectException;
import twisk.mondeIG.MondeIG;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Classe EcouteurTemps.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurTemps implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Constructeur EcouteurTemps.
     *
     * @param monde MondeIG
     */
    public EcouteurTemps(MondeIG monde) {
        this.monde = monde;
    }

    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("2");
        dialog.setTitle("Temps");
        dialog.setHeaderText("Donnez le temps de votre activité");
        dialog.setContentText("Temps :");
        Optional<String> result = dialog.showAndWait();

        try {
            if (result.isPresent()) {
                monde.modiftemps(Integer.valueOf(result.get()), monde.getSelectedEtape().get(0));
            }
        } catch (NoSuchElementException | NumberFormatException | TempsIncorrectException e) {
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

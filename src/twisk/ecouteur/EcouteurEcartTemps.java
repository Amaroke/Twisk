package twisk.ecouteur;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.TwiskException.EcartTempsException;
import twisk.mondeIG.MondeIG;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Classe EcouteurEcartTemps.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurEcartTemps implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Constructeur EcouteurEcartTemps.
     *
     * @param monde MondeIG
     */
    public EcouteurEcartTemps(MondeIG monde) {
        this.monde = monde;
    }

    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Réglage écart temps");
        dialog.setHeaderText("Donnez l'écart temps de votre activité");
        dialog.setContentText("Écart temps :");
        Optional<String> result = dialog.showAndWait();

        try {
            if (result.isPresent()) {
                monde.modifecarttemps(Integer.valueOf(result.get()), monde.getSelectedEtape().get(0));
                monde.notifierObservateur();
            }
        } catch (NoSuchElementException | NumberFormatException | EcartTempsException e) {
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

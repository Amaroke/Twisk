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
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurEcartTemps implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Constructeur EcouteurEcartTemps.
     *
     * @param m MondeIG
     */
    public EcouteurEcartTemps(MondeIG m) {
        this.monde = m;
    }

    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("EcartTemps");
        dialog.setHeaderText("Donnez l'ecart temps de votre activitée");
        dialog.setContentText("Ecart temps :");
        Optional<String> result = dialog.showAndWait();

        try {
            monde.modifecarttemps(Integer.valueOf(result.get()),monde.getSelectedEtape().get(0));
        } catch (NoSuchElementException | NumberFormatException | EcartTempsException e) {
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

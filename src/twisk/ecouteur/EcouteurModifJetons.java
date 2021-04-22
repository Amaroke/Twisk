package twisk.ecouteur;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Classe EcouteurTemps.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurModifJetons implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Constructeur EcouteurTemps.
     *
     * @param monde MondeIG
     */
    public EcouteurModifJetons(MondeIG monde) {
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
        dialog.setTitle("Nombre de Jetons");
        dialog.setHeaderText("Donnez le nombre de jetons de votre guichet");
        dialog.setContentText("Nombre de jetons :");
        Optional<String> result = dialog.showAndWait();

        try {
            if (result.isPresent()) {
                monde.modifNbJetons(Integer.valueOf(result.get()), (GuichetIG) monde.getSelectedEtape().get(0));
                monde.notifierObservateur();
            }
        } catch (NoSuchElementException | NumberFormatException e) {
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

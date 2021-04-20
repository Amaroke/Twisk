package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

/**
 * Classe EcouteurRenomer.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcouteurRenomer implements EventHandler<ActionEvent> {
    private final MondeIG monde;

    /**
     * Constructeur EcouteurRenomer.
     *
     * @param m MondeIG
     */
    public EcouteurRenomer(MondeIG m) {
        this.monde = m;
    }


    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Renommer");
        dialog.setHeaderText("Renommer votre activitée");
        dialog.setContentText("Nom :");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> monde.getSelectedEtape().get(0).setNom(name));
        monde.notifierObservateur();
    }
}

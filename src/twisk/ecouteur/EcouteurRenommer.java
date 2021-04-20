package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

/**
 * Classe EcouteurRenomer.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcouteurRenommer implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Constructeur EcouteurRenomer.
     *
     * @param monde MondeIG
     */
    public EcouteurRenommer(MondeIG monde) {
        this.monde = monde;
    }

    /**
     * Fonction handle.
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Renommage");
        dialog.setHeaderText("Renommer votre activité");
        dialog.setContentText("Nom :");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> monde.getSelectedEtape().get(0).setNom(name));
        monde.notifierObservateur();
    }
}

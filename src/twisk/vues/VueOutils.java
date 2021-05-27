package twisk.vues;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import twisk.exceptions.TwiskException.MondeException;
import twisk.mondeIG.MondeIG;
import twisk.outils.GestionnaireThreads;

import java.util.Objects;

/**
 * Classe VueOutils.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueOutils extends TilePane implements Observateur {

    private final MondeIG m;
    private final Button plus;
    private final Button plusGuichet;
    private final Button simulation;

    /**
     * Constructeur VueOutils.
     *
     * @param monde MondeIG
     */
    public VueOutils(MondeIG monde) {
        m = monde;

        Tooltip tooltip = new Tooltip("Permet d'ajouter une activitée");
        plus = new Button("");
        plus.setId("plusbutton");
        plus.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/plus.png")), 50, 50, true, true)));
        plus.setTooltip(tooltip);
        plus.setOnAction(actionEvent -> m.ajouter("Activite"));

        Tooltip tooltipGuichet = new Tooltip("Permet d'ajouter un guichet dans le monde");
        plusGuichet = new Button("");
        plusGuichet.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/guichet.png")), 50, 50, true, true)));
        plusGuichet.setTooltip(tooltipGuichet);
        plusGuichet.setId("plusbutton");
        plusGuichet.setOnAction(actionEvent -> m.ajouter("Guichet"));

        Tooltip tooltipSim = new Tooltip("Permet de simuler le monde");
        simulation = new Button("");
        simulation.setTooltip(tooltipSim);
        simulation.setId("plusbutton");
        simulation.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/simuler.png")), 50, 50, true, true)));
        simulation.setOnAction(actionEvent -> {
            try {
                m.simuler();
            } catch (MondeException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Monde Incorrect");
                alert.setContentText(e.getMessage());
                alert.show();
                PauseTransition p = new PauseTransition(Duration.seconds(4));
                p.setOnFinished(event -> alert.close());
                p.play();
            }
        });

        m.ajouterObservateur(this);
        reagir();
    }

    @Override
    public void reagir() {
        Runnable command = () -> {
            this.getChildren().clear();
            if(m.getSimulation() != null) {
            if (m.isSimulationStart()) {
                plus.setDisable(true);
                plusGuichet.setDisable(true);
                simulation.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/stop.png")), 50, 50, true, true)));
                simulation.setOnAction(actionEvent -> {
                    GestionnaireThreads.getInstance().detruireTout();
                    m.setSimulationStart(false);
                    m.notifierObservateur();
                });
            } else {
                plus.setDisable(false);
                plusGuichet.setDisable(false);
                simulation.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/simuler.png")), 50, 50, true, true)));
                simulation.setOnAction(actionEvent -> {
                    try {
                        m.simuler();
                    } catch (MondeException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Monde Incorrect");
                        alert.setContentText(e.getMessage());
                        alert.show();
                        PauseTransition p = new PauseTransition(Duration.seconds(4));
                        p.setOnFinished(event -> alert.close());
                        p.play();
                    }
                });
            }}
            this.getChildren().addAll(plus, plusGuichet, simulation);
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }

    }
}

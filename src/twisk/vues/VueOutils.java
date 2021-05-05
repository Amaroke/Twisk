package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.exceptions.TwiskException.MondeException;
import twisk.mondeIG.MondeIG;

import java.util.Objects;

/**
 * Classe VueOutils.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueOutils extends TilePane implements Observateur {
    private final MondeIG m;

    /**
     * Constructeur VueOutils.
     *
     * @param monde MondeIG
     */
    public VueOutils(MondeIG monde) {
        m = monde;
        setButton();
    }

    /**
     * Setter boutons.
     */
    public void setButton() {
        Tooltip tooltip = new Tooltip("Permet d'ajouter une activitée");
        Button b = new Button("");
        b.setId("plusbutton");
        b.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/plus.png")), 50, 50, true, true)));
        b.setTooltip(tooltip);
        b.setOnAction(actionEvent -> m.ajouter("Activite"));

        Tooltip tooltipGuichet = new Tooltip("Permet d'ajouter un guichet dans le monde");
        Button bguichet = new Button("");
        bguichet.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/guichet.png")), 50, 50, true, true)));
        bguichet.setTooltip(tooltipGuichet);
        bguichet.setId("plusbutton");
        bguichet.setOnAction(actionEvent -> m.ajouter("Guichet"));

        Tooltip tooltipSim = new Tooltip("Permet de simuler le monde");
        Button bsim = new Button("");
        bsim.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/simuler.png")), 50, 50, true, true)));
        bsim.setTooltip(tooltipSim);
        bsim.setId("plusbutton");
        bsim.setOnAction(actionEvent -> {
            try {
                m.simuler();
            } catch (MondeException e) {
                e.printStackTrace();
            }
        });

        this.getChildren().addAll(b, bguichet, bsim);
    }

    @Override
    public void reagir() {
    }
}

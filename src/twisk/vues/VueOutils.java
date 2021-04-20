package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.mondeIG.MondeIG;

/**
 * Classe VueOutils.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class VueOutils extends TilePane implements Observateur {
    private final MondeIG m;
    private Button b;
    private Button bguichet;

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
        b = new Button("");
        b.setId("plusbutton");
        b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/twisk/ressources/images/plus.png"), 50, 50, true, true)));
        b.setTooltip(tooltip);
        b.setOnAction(actionEvent -> m.ajouter("Activite"));
        Tooltip tooltipb = new Tooltip("Permet d'ajouter un guichet dans le monde");
        bguichet = new Button("");
        bguichet.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/twisk/ressources/images/guichet.png"), 50, 50, true, true)));
        bguichet.setTooltip(tooltipb);
        bguichet.setId("plusbutton");
        this.getChildren().addAll(b,bguichet);
    }

    @Override
    public void reagir() {

    }
}

package twisk.vues;

import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Classe VueActiviteIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueActiviteIG extends VueEtapeIG implements Observateur {

    /**
     * Constructeur de la VueActiviteIG.
     *
     * @param monde MondeIG
     * @param etape EtapeIG
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        construireFenetre();
    }

    /**
     * Construction de la fenetre.
     */
    public void construireFenetre() {
        HBox hbox = new HBox();
        hbox.setPrefSize(this.e.getLargeur(), this.e.getHauteur());
        hbox.setId("hbox");
        this.getChildren().addAll(this.l, hbox);
    }

    @Override
    public void reagir() {
    }
}

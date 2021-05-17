package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

import java.util.ArrayList;

/**
 * Classe VueActiviteIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueGuichetIG extends VueEtapeIG implements Observateur {

    /**
     * Constructeur de la VueActiviteIG.
     *
     * @param monde MondeIG
     * @param etape EtapeIG
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        construireFenetre();
    }

    /**
     * Construction de la fenetre.
     */
    public void construireFenetre() {
        GuichetIG guichet = (GuichetIG) getEtape();
        l = new Label(guichet.getNom() + ", NbJetons : " + guichet.getNbJetons());
        this.getChildren().add(this.l);
        HBox hbox = new HBox();
        hbox.setPrefSize(this.e.getLargeur(), this.e.getHauteur());
        ArrayList<HBox> list = new ArrayList<>(10);
        for(int i = 0; i < guichet.getNbJetons(); i++){
            HBox square = new HBox();
            square.setPrefSize((float) this.e.getLargeur()/guichet.getNbJetons(), (float) this.e.getHauteur()/ guichet.getNbJetons());
            square.setId("hboxguichet");
            list.add(square);
        }
        hbox.getChildren().addAll(list);
        this.getChildren().add(hbox);
    }

    @Override
    public void reagir() {
    }
}

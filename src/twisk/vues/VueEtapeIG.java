package twisk.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twisk.ecouteur.EcouteurDragDetected;
import twisk.ecouteur.EcouteurEtape;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.Objects;

/**
 * Classe FabriqueIdentifiant.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public abstract class VueEtapeIG extends VBox implements Observateur {

    protected MondeIG m;
    protected EtapeIG e;
    protected Label l;

    /**
     * Constructeur VueEtapeIG.
     *
     * @param monde MondeIG
     * @param etape EtapeIG
     */
    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        setPadding(new Insets(3, 3, 3, 3));
        this.setPrefWidth(etape.getLargeur());
        this.setPrefHeight(etape.getHauteur());
        m = monde;
        e = etape;
        this.setId(e.getIdentifiant());
        if (!e.getSelectionne() && e.estUneActivite()) {
            this.getStyleClass().add("backetape");
        }else if (!e.getSelectionne() && e.estUnGuichet()){
            this.getStyleClass().add("backguichet");
        }
        else {
            this.getStyleClass().add("selected");
        }
        constructionLogo();
        this.relocate(etape.getPosX(), etape.getPosY());
        if(m.getSimulation() == null || !m.isSimulationStart()) {
            this.setOnMouseClicked(new EcouteurEtape(e, m));
            this.setOnDragDetected(new EcouteurDragDetected(this));
        }
    }

    /**
     * Fonction construction graphique des entrés/sorties du monde
     */
    public void constructionLogo() {
        HBox entre_sortie = new HBox();
        if (this.getEtape().getEstUneEntre()) {
            Label open = new Label("");
            open.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/enter.png")))));
            entre_sortie.getChildren().add(open);
        }
        if (this.getEtape().getEstUneSortie()) {
            Label close = new Label("");
            close.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/exit.png")))));
            entre_sortie.getChildren().add(close);
        }
        entre_sortie.setAlignment(Pos.CENTER);
        this.getChildren().add(entre_sortie);
    }

    /**
     * Getter etape.
     *
     * @return EtapeIG
     */
    public EtapeIG getEtape() {
        return e;
    }

    /**
     * Getter du monde.
     * @return MondeIG
     */
    public MondeIG getMonde(){
        return m;
    }
}

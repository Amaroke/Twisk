package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.ecouteur.EcouteurDragDropped;
import twisk.ecouteur.EcouteurDragOver;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Classe VueMondeIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueMondeIG extends Pane implements Observateur {
    private final MondeIG m;

    /**
     * Constructeur VueMondeIG.
     *
     * @param monde MondeIG
     */
    public VueMondeIG(MondeIG monde) {
        this.m = monde;
        this.m.ajouterObservateur(this);
        for (int i = 0; i < m.nbEtapes(); i++) {
            EtapeIG ids = m.getEtape().get(String.valueOf(i));
            VueEtapeIG vue = new VueActiviteIG(m, ids);
            this.getChildren().add(vue);
        }
        this.setOnDragOver(new EcouteurDragOver());
        this.setOnDragDropped(new EcouteurDragDropped(m, this));
        reagir();
    }


    /**
     * Fonction réaction/création des arc + pdc.
     */
    @Override
    public void reagir() {
        this.getChildren().clear();
        for (int i = 0; i < m.getArc().size(); i++) {
            VueArcIG a = new VueArcIG(m.getArc().get(i), m);
            this.getChildren().add(a);
        }
        for (EtapeIG e : m) {
            VueEtapeIG vue = new VueActiviteIG(m, e);
            this.getChildren().add(vue);
            for (int j = 0; j < 4; j++) {
                VuePointDeControleIG pdc = new VuePointDeControleIG(m, e.getPdc(j));
                this.getChildren().add(pdc);
            }
        }
    }
}

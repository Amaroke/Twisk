package twisk.vues;

import javafx.scene.shape.Circle;
import twisk.ecouteur.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * Classe VuePointDeControleIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VuePointDeControleIG extends Circle implements Observateur {

    /**
     * Constructeur VuePointDeControleIG
     *
     * @param monde MondeIG
     * @param pdc   PointDeControleIG
     */
    public VuePointDeControleIG(MondeIG monde, PointDeControleIG pdc) {
        this.setCenterX(pdc.getPosX());
        this.setCenterY(pdc.getPosY());
        this.setId("pdc");
        this.setRadius(5);
        this.setOnMouseClicked(new EcouteurPointDeControle(pdc, monde));
    }

    @Override
    public void reagir() {
    }

}

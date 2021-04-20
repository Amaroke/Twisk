package twisk.vues;

import javafx.scene.shape.Circle;
import twisk.ecouteur.EcouteurPointDeControle;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * Classe VuePointDeControleIG.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class VuePointDeControleIG extends Circle implements Observateur {
    private final MondeIG m;
    private final EtapeIG e;
    private final PointDeControleIG pdc;

    /**
     * Constructeur VuePointDeControleIG
     *
     * @param monde MondeIG
     * @param etape EtapeIG
     * @param pdc   PointDeControleIG
     */
    public VuePointDeControleIG(MondeIG monde, EtapeIG etape, PointDeControleIG pdc) {
        m = monde;
        e = etape;
        this.pdc = pdc;
        this.setCenterX(pdc.getPosX());
        this.setCenterY(pdc.getPosY());
        this.setId("pdc");
        this.setRadius(5);
        this.setOnMouseClicked(new EcouteurPointDeControle(pdc, m));
    }


    @Override
    public void reagir() {

    }
}

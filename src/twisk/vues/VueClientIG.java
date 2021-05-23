package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.EtapeIG;

/**
 * Classe VueClientIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueClientIG extends Circle implements Observateur {

    public VueClientIG(EtapeIG etape) {
        Circle clientRond = new Circle(etape.getPosX(), etape.getPosY(), 10.0);
        clientRond.setFill(Color.GREEN);
    }

    @Override
    public void reagir() {
    }

}

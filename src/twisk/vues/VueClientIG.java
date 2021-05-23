package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.simulation.Client;

/**
 * Classe VueClientIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueClientIG extends Circle implements Observateur {

    public VueClientIG(Client c) {
        this.setRadius(4d);
        switch(c.getNumeroClient() % 10) {
            case 1:
                this.setFill(Color.BLACK);
                break;
            case 2:
                this.setFill(Color.RED);
                break;
            case 3:
                this.setFill(Color.GREEN);
                break;
            case 4:
                this.setFill(Color.BLUE);
                break;
            case 5:
                this.setFill(Color.YELLOW);
                break;
            case 6:
                this.setFill(Color.PURPLE);
                break;
            case 7:
                this.setFill(Color.ORANGE);
                break;
            case 8:
                this.setFill(Color.GREY);
                break;
            case 9:
                this.setFill(Color.BROWN);
                break;
            default:
                this.setFill(Color.PINK);
                break;
        }
    }

    @Override
    public void reagir() {
    }

}

package twisk.mondeIG;

import java.io.Serializable;

/**
 * Classe ArcIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach
 * @version 1.0
 */

public class ArcIG implements Serializable {

    private final PointDeControleIG[] pos = new PointDeControleIG[2];
    private boolean selectionne;

    /**
     * Constructeur ArcIG.
     *
     * @param p1 PointDeControleIG
     * @param p2 PointDeControleIG
     */
    public ArcIG(PointDeControleIG p1, PointDeControleIG p2) {
        pos[0] = p1;
        pos[1] = p2;
        selectionne = false;
    }

    /**
     * Getter point.
     *
     * @param i int
     * @return retourne le point à la position i
     */
    public PointDeControleIG getPoint(int i) {
        return pos[i];
    }

    /**
     * Setter point.
     */
    public void setSelectionne() {
        selectionne = !getSelectionne();
    }

    /**
     * Getter selection.
     *
     * @return vrai si selectionné sinon false
     */
    public boolean getSelectionne() {
        return selectionne;
    }
}

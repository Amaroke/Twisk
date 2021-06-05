package twisk;

import java.io.Serializable;

/**
 * Classe TailleComposant.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class TailleComposants implements Serializable {

    private static final TailleComposants instance = new TailleComposants();

    /**
     * Getter de l'instance du singleton.
     * @return
     */
    public static TailleComposants getInstance() {
        return instance;
    }

    /**
     * Getter de la longueur de la Vbox.
     * @return int
     */
    public int getVBoxLong() {
        return 200;
    }

    /**
     * Getter de la largeur de la Vbox
     * @return int
     */
    public int getVBoxLarg() {
        return 75;
    }
}

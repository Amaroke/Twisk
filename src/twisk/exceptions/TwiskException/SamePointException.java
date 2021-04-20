package twisk.exceptions.TwiskException;

/**
 * Exception SamePointException.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class SamePointException extends TwiskException {

    /**
     * Constucteur SamePointException
     */
    public SamePointException() {
        super("Désolé mais vous ne pouvez pas creer une arc sur le même points/étape");
    }
}

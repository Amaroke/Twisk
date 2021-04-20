package twisk.exceptions.TwiskException;

/**
 * Exception SamePointException.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class SamePointException extends TwiskException {

    /**
     * Constucteur SamePointException
     */
    public SamePointException() {
        super("Impossible de lier deux points d'une même étape, ou deux points identiques.");
    }
}

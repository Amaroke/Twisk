package twisk.exceptions.TwiskException;

/**
 * Exception AlreadyExistException.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class AlreadyExistException extends TwiskException {

    /**
     * Constructeur AlreadyExistException.
     */
    public AlreadyExistException() {
        super("Création impossible, un arc équivalent existe déjà.");
    }
}

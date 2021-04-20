package twisk.exceptions.TwiskException;

/**
 * Exception AlreadyExistException.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class AlreadyExistException extends TwiskException {

    /**
     * Constructeur AlreadyExistException.
     */
    public AlreadyExistException() {
        super("La création de ce point n'est pas possible un arc possedant les coordonnés existe déjà");
    }
}

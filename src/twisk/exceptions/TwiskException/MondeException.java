package twisk.exceptions.TwiskException;

/**
 * Exception MondeException.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class MondeException extends TwiskException {

    /**
     * Constucteur SamePointException
     */
    public MondeException() {
        super("Votre monde doit disposer d'au moins une entrée,\nune sortie et d'une activitée !");
    }
}
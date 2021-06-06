package twisk.exceptions.TwiskException;

/**
 * Exception EcartTempsException.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class EcartTempsException extends TwiskException {

    /**
     * Constructeur EcartTempsException
     */
    public EcartTempsException() {
        super("L'écart temps ne peut pas être supérieur ou égal au temps ni être négatif.");
    }
}

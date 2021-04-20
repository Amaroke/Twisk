package twisk.exceptions.TwiskException;

/**
 * Exception EcartTempsException.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class EcartTempsException extends TwiskException{

    /**
     * Constructeur EcartTempsException
     */
    public EcartTempsException() {
        super("Désolé mais le l'écart temps ne peut pas être supérieur ou égal au temps et ne peut pas être négatif");
    }
}

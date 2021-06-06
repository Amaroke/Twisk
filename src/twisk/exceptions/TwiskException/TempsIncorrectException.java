package twisk.exceptions.TwiskException;

/**
 * Exception TempsIncorrectException.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */

public class TempsIncorrectException extends TwiskException {

    /**
     * Constructeur TempsIncorrectException.
     */
    public TempsIncorrectException() {
        super("Le temps ne peut être négatif ou inférieur à 0 !");
    }
}

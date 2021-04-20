package twisk.exceptions.TwiskException;

/**
 * Exception TempsIncorrectException.
 *
 * @author Lambert Calvin
 * @version 1.0
 */

public class TempsIncorrectException extends TwiskException {

    /**
     * Constructeur TempsIncorrectException.
     */
    public TempsIncorrectException() {
        super("Désolé mais vous ne pouvez pas donner un chiffre négatif ou égale à 0 !");
    }
}

package twisk.exceptions.TwiskException;

/**
 * Exception InvalidNumberClient.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.1
 */


public class InvalidNumberClient extends TwiskException{

    /**
     * Constructeur de InvalidNumberClient
     */
    public InvalidNumberClient() {
        super("Le nombre de client saisis est invalide il doit être compris entre 1 et 20");
    }
}

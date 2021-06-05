package twisk.exceptions.TwiskException;

public class InvalidNumberClient extends TwiskException{

    public InvalidNumberClient() {
        super("Le nombre de client saisis est invalide il doit être compris entre 1 et 20");
    }
}

package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

/**
 * Classe GestionnaireThreads
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

@SuppressWarnings("rawtypes")
public class GestionnaireThreads {

    private static final GestionnaireThreads instance = new GestionnaireThreads();
    private final ArrayList<Thread> threads;

    /**
     * Constructeur du gestionnaire de Threads.
     */
    public GestionnaireThreads() {
        threads = new ArrayList<>(10);
    }

    /**
     * Getter de l'instance du singleton.
     * @return GestionnaireThreads
     */
    public static GestionnaireThreads getInstance() {
        return instance;
    }

    /**
     * Fonction de lancement des task dans le thread.
     * @param task Task
     */
    public void lancer(Task task) {
        Thread thread = new Thread(task);
        threads.add(thread);
        thread.start();
    }

    /**
     * Fonction de destruction des task dans le thread
     */
    public void detruireTout() {
        System.out.println(threads);
        for(Thread thread : threads) {
            thread.interrupt();
        }
        System.out.println(threads);
        threads.clear();
        System.out.println(threads);
    }
}

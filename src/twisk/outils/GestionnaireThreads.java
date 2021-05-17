package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class GestionnaireThreads {

    private static final GestionnaireThreads instance = new GestionnaireThreads();
    private final ArrayList<Thread> threads;

    public GestionnaireThreads() {
        threads = new ArrayList<>(10);
    }

    public static GestionnaireThreads getInstance() {
        return instance;
    }

    public void lancer(Task task) {
        Thread thread = new Thread(task);
        threads.add(thread);
        thread.start();
    }

    public void detruireTout() {
        for(Thread thread : threads) {
            thread.interrupt();
        }
    }
}

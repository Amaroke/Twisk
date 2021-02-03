package twisk.outils;

public class FabriqueNumero {

    private static final FabriqueNumero instance = new FabriqueNumero();
    int cptEtape;
    int cptSemaphore;

    private FabriqueNumero() {
        cptEtape = 0;
        cptSemaphore = 1;
    }

    public static FabriqueNumero getInstance() {
        return instance;
    }

    public int getNumeroEtape() {
        cptEtape++;
        return cptEtape-1;
    }

    public int getNumeroSemaphore() {
        cptSemaphore++;
        return cptSemaphore-1;
    }

    void reset() {
        cptEtape = 0;
    }
}
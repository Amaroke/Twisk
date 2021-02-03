package twisk.outils;

public class FabriqueNumero {

    private static final FabriqueNumero instance = new FabriqueNumero();
    int cptEtape;

    private FabriqueNumero() {
        cptEtape = 0;
    }

    public static FabriqueNumero getInstance() {
        return instance;
    }

    public int getNumeroEtape() {
        cptEtape++;
        return cptEtape-1;
    }

    void reset() {
        cptEtape = 0;
    }
}
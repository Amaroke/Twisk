package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape {

    int nbJetons;
    int semaphore;

    public Guichet(String nom) {
        super(nom);
    }

    public Guichet(String nom, int nb) {
        super(nom);
        this.nbJetons = nb;
        FabriqueNumero singleton = FabriqueNumero.getInstance();
        this.semaphore = singleton.getNumeroSemaphore();
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    @Override
    public String toString() {
        return "Étape N°" + getNum() + " " + getNom() + " (" + getNbJetons() + " jetons et Semaphore n°" + getSemaphore() + ")";
    }

    @Override
    public String toC() {
        return "P(" + semaphore + "," + nbJetons + "); \n" +
                "transfert(" + num + "," + getGestionnaireSuccesseurs().getEtapes().get(0).getNum() + "); \n" +
                "V(" + semaphore + "," + nbJetons + ");\n" + getGestionnaireSuccesseurs().getEtapes().get(0).toC();
    }

    public int getSemaphore() {
        return semaphore;
    }

    public int getNbJetons() {
        return nbJetons;
    }
}

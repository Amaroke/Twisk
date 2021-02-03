package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape {

    int nbJetons;
    int semaphore;

    public Guichet(String nom) {
        super(nom);
    }

    public Guichet(String nom, int nb){
        super(nom);
        this.nbJetons = nb;
        FabriqueNumero singleton = FabriqueNumero.getInstance();
        this.semaphore = singleton.getNumeroSemaphore();
    }

    @Override
    public boolean estUnGuichet(){
        return true;
    }

    public int getSemaphore(){
        return semaphore;
    }

}

package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable {

    public GestionnaireEtapes gestionnaireEtapes;
    public SasEntree sasEntree;
    public SasSortie sasSortie;

    public Monde(){

    }

    void aCommeEntree(Etape... etapes){

    }

    void aCommeSortie(Etape... etapes){

    }

    void ajouter(Etape... etapes){

    }

    int nbEtapes(){
        return 0;
    }

    int nbGuichets(){
        return 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}

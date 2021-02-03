package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    public GestionnaireEtapes gestionnaireEtapes;
    public SasEntree sasEntree;
    public SasSortie sasSortie;

    public Monde(){
        sasEntree = new SasEntree();
        sasSortie = new SasSortie();
        gestionnaireEtapes = new GestionnaireEtapes();
    }

    void aCommeEntree(Etape... etapes){
        sasEntree.ajouterSuccesseur(etapes);
    }

    void aCommeSortie(Etape... etapes){
        for (Etape etape: etapes) {
           etape.ajouterSuccesseur(sasSortie);
        }
    }

    void ajouter(Etape... etapes){
        gestionnaireEtapes.ajouter(etapes);
    }

    int nbEtapes(){
        return gestionnaireEtapes.nbEtapes();
    }

    int nbGuichets(){
        int guichet = 0;
        for (Etape etape : this) {
            if (etape.estUnGuichet()) {
                guichet++;
            }
        }
        return guichet;
    }

    @Override
    public Iterator<Etape> iterator() {
        return gestionnaireEtapes.iterator();
    }

    public Etape getEtape(int i){
        return gestionnaireEtapes.etapes.get(i);
    }

}

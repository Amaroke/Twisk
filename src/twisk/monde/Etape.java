package twisk.monde;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {

    public String nom;
    public GestionnaireSuccesseurs gestionnaireSuccesseurs;

    public Etape(String nom) {
        this.nom = nom;
        this.gestionnaireSuccesseurs = new GestionnaireSuccesseurs();
    }

    public void ajouterSuccesseur(Etape... e) {
        gestionnaireSuccesseurs.ajouter(e);
    }

    public boolean estUneActivite() {
        return false;
    }

    public boolean estUnGuichet() {
        return false;
    }

    public int nbSuccesseurs(){
        return gestionnaireSuccesseurs.nbEtapes();
    }

    @Override
    public Iterator<Etape> iterator() {
        return gestionnaireSuccesseurs.iterator();
    }

    @Override
    public String toString() {
        return "Activité : " + nom + " - ";
    }

}

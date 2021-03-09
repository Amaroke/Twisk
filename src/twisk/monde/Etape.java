package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {

    public String nom;
    public GestionnaireSuccesseurs gestionnaireSuccesseurs;
    public int num;

    public Etape(String nom) {
        this.nom = nom;
        this.gestionnaireSuccesseurs = new GestionnaireSuccesseurs();
        FabriqueNumero singleton = FabriqueNumero.getInstance();
        this.num = singleton.getNumeroEtape();
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

    public int nbSuccesseurs() {
        return gestionnaireSuccesseurs.nbEtapes();
    }

    @Override
    public Iterator<Etape> iterator() {
        return gestionnaireSuccesseurs.iterator();
    }

    @Override
    public String toString() {
        return "N° : " + getNum() + getNom();
    }

    public String getNom() {
        return nom;
    }

    public GestionnaireSuccesseurs getGestionnaireSuccesseurs() {
        return gestionnaireSuccesseurs;
    }

    public String toC(){
        return "";
    }

    public int getNum() {
        return num;
    }

    public String getSuccesseursString() {
        return gestionnaireSuccesseurs.toString();
    }


}

package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

/**
 * Classe Etape
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public abstract class Etape implements Iterable<Etape> {

    public String nom;
    public GestionnaireSuccesseurs gestionnaireSuccesseurs;
    public int num;

    /**
     * Constructeur Etape avec nom.
     *
     * @param nom Nom de l'étape
     */
    public Etape(String nom) {
        this.nom = nom;
        this.gestionnaireSuccesseurs = new GestionnaireSuccesseurs();
        FabriqueNumero singleton = FabriqueNumero.getInstance();
        this.num = singleton.getNumeroEtape();
    }

    /**
     * Ajout de successeurs à l'étape.
     *
     * @param e Ajout d'un successeur à l'étape actuelle
     */
    public void ajouterSuccesseur(Etape... e) {
        getGestionnaireSuccesseurs().ajouter(e);
    }

    /**
     * Getter estUneActivite.
     *
     * @return Vrai si c'est une activité sinon faux
     */
    public boolean estUneActivite() {
        return false;
    }

    /**
     * Getter estUnGuichet.
     *
     * @return Vrai si c'est un guichet sinon faux
     */
    public boolean estUnGuichet() {
        return false;
    }

    /**
     * Getter nombre de successeurs.
     *
     * @return Retourne le nombre de successeurs à l'étape
     */
    public int nbSuccesseurs() {
        return getGestionnaireSuccesseurs().nbEtapes();
    }

    /**
     * Fonction itérateur d'étape.
     *
     * @return L'itérateur d'étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return getGestionnaireSuccesseurs().iterator();
    }

    /**
     * Fonction de mise en string de l'étape.
     *
     * @return Un string de l'étape contenant son numéro et son nom
     */
    @Override
    public String toString() {
        return "N° : " + getNum() + getNom();
    }

    /**
     * Fonction de mise en code c de étape.
     *
     * @return Un string contenant le code C de l'étape
     */
    public String toC() {
        return "";
    }

    /**
     * Getter de numéro de l'étape.
     *
     * @return Le numéro de l'étape
     */
    public int getNum() {
        return num;
    }

    /**
     * Getter du nom de l'étape.
     *
     * @return Le nom de l'étape
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter du gestionnaire de successeurs.
     *
     * @return Le gestionnaire de successeurs de l'étape
     */
    public GestionnaireSuccesseurs getGestionnaireSuccesseurs() {
        return gestionnaireSuccesseurs;
    }

    /**
     * Getter du gestionnaire de successeurs en format string.
     */
    public String getSuccesseursString() {
        return getGestionnaireSuccesseurs().toString();
    }

    /**
     * Getter de l'étape suivante.
     *
     * @return L'étape suivante
     */
    public Etape getSuivant() {
        return getGestionnaireSuccesseurs().getEtapes().get(0);
    }
}

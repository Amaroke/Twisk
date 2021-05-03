package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

/**
 * Classe Monde
 *
 * @author Mathieu Steinbach Hugo & Lambert Calvin
 * @version 1.0
 */

public class Monde implements Iterable<Etape> {

    private final GestionnaireEtapes gestionnaireEtapes;
    private final SasEntree sasEntree;
    private final SasSortie sasSortie;

    /**
     * Constructeur du Monde.
     */
    public Monde() {
        FabriqueNumero singleton = FabriqueNumero.getInstance();
        singleton.reset();
        sasEntree = new SasEntree();
        sasSortie = new SasSortie();
        gestionnaireEtapes = new GestionnaireEtapes();
        this.ajouter(getSasEntree(), getSasSortie());
    }

    /**
     * Setter des entrées du monde.
     *
     * @param etapes Collection d'étapes servant d'entrée
     */
    public void aCommeEntree(Etape... etapes) {
        getSasEntree().ajouterSuccesseur(etapes);
    }

    /**
     * Setter des sortis du monde.
     *
     * @param etapes Collection d'étapes servant de sortie au monde
     */
    public void aCommeSortie(Etape... etapes) {
        for (Etape etape : etapes) {
            etape.ajouterSuccesseur(getSasSortie());
        }
    }

    /**
     * Ajout d'étapes dans le monde.
     *
     * @param etapes Collection d'étape
     */
    public void ajouter(Etape... etapes) {
        getGestionnaireEtapes().ajouter(etapes);
    }

    /**
     * Getter nombre d'étapes.
     *
     * @return Le nombre d'étapes
     */
    public int nbEtapes() {
        return getGestionnaireEtapes().nbEtapes();
    }

    /**
     * Getter du nombre de guichet dans le monde.
     *
     * @return Un int du nombre de guichet présent dans le monde
     */
    public int nbGuichets() {
        int guichet = 0;
        for (Etape etape : this) {
            if (etape.estUnGuichet()) {
                guichet++;
            }
        }
        return guichet;
    }

    /**
     * Permet de construit le code C du monde.
     *
     * @return Un string contenant le code C du monde
     */
    public StringBuilder toC() {
        StringBuilder s = new StringBuilder();
        s.append("#include <stdlib.h>\n" +
                "#include <stdio.h>\n" +
                "#include <time.h>\n" +
                "#include \"def.h\" \n \n" +
                "void simulation(int ids)" +
                "{\n srand(time(NULL)  + getpid());\n");
        s.append(getSasEntree().toC());
        s.append("}");
        return s;
    }

    /**
     * Iterateur d'etape.
     *
     * @return L'itérateur d'étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return getGestionnaireEtapes().iterator();
    }

    /**
     * Getter d'étape.
     *
     * @param i Numéro de l'étape à récuperer
     * @return Un étape demandé
     */
    public Etape getEtape(int i) {
        Etape etape = null;
        for (int p = 0; p < getGestionnaireEtapes().nbEtapes(); p++) {
            if (getGestionnaireEtapes().getEtapes().get(p).getNum() == i) {
                etape = getGestionnaireEtapes().getEtapes().get(p);
            }
        }
        return etape;
    }

    /**
     * Fonction de mise en string du monde.
     *
     * @return Un string du monde
     */
    @Override
    public String toString() {
        return getGestionnaireEtapes().toString();
    }

    /**
     * Getter de gestionnaire d'étapes.
     *
     * @return Le gestionnaire d'étape du monde
     */
    public GestionnaireEtapes getGestionnaireEtapes() {
        return gestionnaireEtapes;
    }

    /**
     * Getter du sasEntre.
     *
     * @return sasEntre
     */
    public SasEntree getSasEntree() {
        return sasEntree;
    }

    /**
     * Getter du sasSortie.
     *
     * @return sasSortie
     */
    public SasSortie getSasSortie() {
        return sasSortie;
    }
}

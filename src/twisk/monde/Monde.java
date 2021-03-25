package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    public GestionnaireEtapes gestionnaireEtapes;
    public SasEntree sasEntree;
    public SasSortie sasSortie;

    public Monde() {
        sasEntree = new SasEntree();
        sasSortie = new SasSortie();
        gestionnaireEtapes = new GestionnaireEtapes();
        this.ajouter(getSasEntree(), getSasSortie());
    }

    public void aCommeEntree(Etape... etapes) {
        getSasSortie().ajouterSuccesseur(etapes);
    }

    public void aCommeSortie(Etape... etapes) {
        for (Etape etape : etapes) {
            etape.ajouterSuccesseur(getSasSortie());
        }
    }

    public void ajouter(Etape... etapes) {
        getGestionnaireEtapes().ajouter(etapes);
    }

    public int nbEtapes() {
        return getGestionnaireEtapes().nbEtapes();
    }

    public int nbGuichets() {
        int guichet = 0;
        for (Etape etape : this) {
            if (etape.estUnGuichet()) {
                guichet++;
            }
        }
        return guichet;
    }

    public StringBuilder toC() {
        StringBuilder s = new StringBuilder();
        s.append("#include <stdlib.h>\n" +
                "#include <stdio.h>\n" +
                "#include \"def.h\" \n \n" + "void simulation(int ids)" +
                "{ \n");
        s.append(getSasEntree().toC());
        s.append("}");
        return s;
    }

    @Override
    public Iterator<Etape> iterator() {
        return getGestionnaireEtapes().iterator();
    }

    public Etape getEtape(int i) {
        Etape etape = null;
        for (int p = 0; p < getGestionnaireEtapes().nbEtapes(); p++) {
            if (getGestionnaireEtapes().getEtapes().get(p).getNum() == i) {
                etape = getGestionnaireEtapes().getEtapes().get(p);
            }
        }
        return etape;
    }

    @Override
    public String toString() {
        return getGestionnaireEtapes().toString();
    }

    public GestionnaireEtapes getGestionnaireEtapes() {
        return gestionnaireEtapes;
    }

    public SasEntree getSasEntree() {
        return sasEntree;
    }

    public SasSortie getSasSortie() {
        return sasSortie;
    }
}

package twisk.mondeIG;

import twisk.TailleComposants;
import twisk.exceptions.TwiskException.AlreadyExistException;
import twisk.exceptions.TwiskException.EcartTempsException;
import twisk.exceptions.TwiskException.SamePointException;
import twisk.exceptions.TwiskException.TempsIncorrectException;
import twisk.outils.FabriqueIdentifiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe MondeIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class MondeIG extends SujetObserve implements Iterable<EtapeIG> {

    private final ArrayList<EtapeIG> selectedEtape = new ArrayList<>(10);
    private final ArrayList<ArcIG> selectedArc = new ArrayList<>(10);
    private final PointDeControleIG[] pdcCrea = new PointDeControleIG[2];
    private final ArrayList<ArcIG> arc = new ArrayList<>(10);
    private final HashMap<String, EtapeIG> etape = new HashMap<>();
    private final ArrayList<EtapeIG> etapeEntre = new ArrayList<>(5);
    private final ArrayList<EtapeIG> etapeSortie = new ArrayList<>(5);
    private final FabriqueIdentifiant fabriqueID = FabriqueIdentifiant.getInstance();
    private final TailleComposants composants = TailleComposants.getInstance();

    /**
     * Constructeur MondeIG.
     */
    public MondeIG() {
        ActiviteIG activite = new ActiviteIG("Activite : " + 0, fabriqueID.getIdentifiantEtape(), composants.getVBoxLong(), composants.getVBoxLarg());
        etape.put(activite.getIdentifiant(), activite);
    }

    /**
     * Fonction ajout d'activité.
     *
     * @param type String
     */
    public void ajouter(String type) {
        if (type.equals("Activite")) {
            String idf = fabriqueID.getIdentifiantEtape();
            ActiviteIG a = new ActiviteIG("Activite : " + idf, idf, composants.getVBoxLong(), composants.getVBoxLarg());
            etape.put(idf, a);
        } else if (type.equals("Guichet")) {
            // À REMPLACER PAR UN GUICHET
            String idf = fabriqueID.getIdentifiantEtape();
            ActiviteIG a = new ActiviteIG("Activite : " + idf, idf, composants.getVBoxLong(), composants.getVBoxLarg());
            etape.put(idf, a);
        }
        notifierObservateur();
    }

    /**
     * Fonction ajouter PointDeControle.
     *
     * @param pt1 PointDeControleIG
     * @param pt2 PointDeControleIG
     * @throws SamePointException lancement exception si l'arc possède des coordonné déjà existant
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2) throws SamePointException, AlreadyExistException {
        if (pt1.getPosX() == pt2.getPosX() && pt1.getPosY() == pt2.getPosY()
                || pt1.getEtape().getIdentifiant().charAt(0) == pt2.getEtape().getIdentifiant().charAt(0)) {
            throw new SamePointException();
        }

        for (ArcIG e : arc) {
            if (e.getPoint(0).getPosX() == pt1.getPosX() && e.getPoint(0).getPosY() == pt1.getPosY() ||
                    e.getPoint(1).getPosX() == pt2.getPosX() && e.getPoint(1).getPosY() == pt2.getPosY() ||
                    (e.getPoint(1).getPosX() == pt1.getPosX() && e.getPoint(1).getPosY() == pt1.getPosY() ||
                            e.getPoint(0).getPosX() == pt2.getPosX() && e.getPoint(0).getPosY() == pt2.getPosY())) {
                throw new AlreadyExistException();
            }
        }
        arc.add(new ArcIG(pt1, pt2));

    }

    /**
     * Getter du nombre d'étape dans la hashmap d'étape.
     *
     * @return int
     */
    public int nbEtapes() {
        return this.etape.size();
    }

    public void creerArc(PointDeControleIG p) throws AlreadyExistException, SamePointException {
        if (pdcCrea[0] == null) {
            pdcCrea[0] = p;
        } else if (pdcCrea[1] == null) {
            pdcCrea[1] = p;
            try {
                this.ajouter(pdcCrea[0], pdcCrea[1]);
            } finally {
                this.notifierObservateur();
                pdcCrea[0] = null;
                pdcCrea[1] = null;
            }
        }
    }

    /**
     * Fonction d'ajout des Arcs a la liste de gestion des arcs selectionnés.
     *
     * @param arcparam ArcIG
     */
    public void gestionArc(ArcIG arcparam) {
        boolean isIn = false;
        for (ArcIG a : getSelectedArc()) {
            if (a == arcparam) {
                isIn = true;
            }
        }
        if (!isIn) {
            getSelectedArc().add(arcparam);
        } else {
            getSelectedArc().remove(arcparam);
        }
        arcparam.setSelectionne();
        notifierObservateur();
    }

    /**
     * Fonction de deselection supprime la selection et met à jour en conséquence.
     */
    public void gestionDeselection() {
        for (EtapeIG e : getSelectedEtape()) {
            e.setSelectionne();
        }
        getSelectedEtape().clear();
        for (ArcIG arc : getSelectedArc()) {
            arc.setSelectionne();
        }
        getSelectedArc().clear();
        notifierObservateur();
    }

    /**
     * Fonction d'ajout des Etapes dans les étapes sélectionné du monde lors du clique.
     *
     * @param etapeparam EtapeIG
     */
    public void gestionEtape(EtapeIG etapeparam) {
        boolean isIn = false;
        for (EtapeIG e : getSelectedEtape()) {
            if (e.getIdentifiant().equals(etapeparam.getIdentifiant())) {
                isIn = true;
            }
        }
        if (!isIn) {
            getSelectedEtape().add(etapeparam);
        } else {
            getSelectedEtape().remove(etapeparam);
        }
        etapeparam.setSelectionne();
        notifierObservateur();
    }

    /**
     * Fonction gestion de la suppresion des Etapes selectionné.
     */
    public void gestionSuppresion() {
        Iterator<EtapeIG> ite = iterator();
        Iterator<ArcIG> iteArc = iterateurArc();
        while (ite.hasNext()) {
            EtapeIG etape = ite.next();
            if (etape.getSelectionne()) {
                etape.suprPDC();
                ite.remove();
            }
        }
        while (iteArc.hasNext()) {
            ArcIG arc = iteArc.next();
            if (arc.getPoint(0).getEtape().getSelectionne() || arc.getPoint(1).getEtape().getSelectionne()) {
                iteArc.remove();
            }
        }
        getSelectedEtape().clear();
        notifierObservateur();
    }

    /**
     * Fonction gestion pour la suppressions des arcs.
     */
    public void gestionSuppressionArc() {
        Iterator<ArcIG> iteArc = iterateurArc();
        while (iteArc.hasNext()) {
            ArcIG arc = iteArc.next();
            if (arc.getSelectionne()) {
                iteArc.remove();
            }
        }
        getSelectedArc().clear();
        notifierObservateur();
    }

    /**
     * Gestion des entree dans le monde
     */
    public void gestionEntre() {
        boolean flag = false;
        for (EtapeIG e : getSelectedEtape()) {
            for (EtapeIG entre : getEtapeEntre()) {
                if (e == entre) {
                    flag = true;
                }
            }
            if (flag) {
                getEtapeEntre().remove(e);
                e.setEstEntre();
                flag = false;
            } else {
                getEtapeEntre().add(e);
                e.setEstEntre();
            }
        }
        this.notifierObservateur();
    }


    /**
     * Gestion des sortis dans le monde
     */
    public void gestionSortie() {
        boolean flag = false;
        for (EtapeIG e : getSelectedEtape()) {
            for (EtapeIG sortie : getEtapeSortie()) {
                if (e == sortie) {
                    flag = true;
                }
            }
            if (flag) {
                getEtapeSortie().remove(e);
                e.setEstSortie();
                flag = false;
            } else {
                getEtapeSortie().add(e);
                e.setEstSortie();
            }
        }
        this.notifierObservateur();
    }

    /**
     * Fonction iterateur des valeurs de la hashmap d'étape.
     *
     * @return Iterator<EtapeIG>
     */
    @Override
    public Iterator<EtapeIG> iterator() {
        return etape.values().iterator();
    }

    /**
     * Fonction itérateur de arc.
     *
     * @return Iterator<ArcIG>
     */
    public Iterator<ArcIG> iterateurArc() {
        return arc.iterator();
    }

    /**
     * Getter de la hashmap d'étape.
     *
     * @return HashMap<String, EtapeIG>
     */
    public HashMap<String, EtapeIG> getEtape() {
        return etape;
    }

    /**
     * Getter ArrayList d'arc.
     *
     * @return ArrayList<ArcIG>
     */
    public ArrayList<ArcIG> getArc() {
        return arc;
    }

    /**
     * Getter ArrayList d'étape selectionné.
     *
     * @return ArrayList<EtapeIG>
     */
    public ArrayList<EtapeIG> getSelectedEtape() {
        return selectedEtape;
    }

    /**
     * Getter ArrayList des arcs selectionné.
     *
     * @return ArrayList<ArcIG>
     */
    public ArrayList<ArcIG> getSelectedArc() {
        return selectedArc;
    }

    /**
     * Getter ArrayList des etapes de sortie.
     *
     * @return ArrayList<EtapeIG>
     */
    public ArrayList<EtapeIG> getEtapeSortie() {
        return etapeSortie;
    }

    /**
     * Getter ArrayList des etapes en entrée.
     *
     * @return ArrayList<EtapeIG>
     */
    public ArrayList<EtapeIG> getEtapeEntre() {
        return etapeEntre;
    }

    /**
     * Fonction modification du temps des etapes.
     *
     * @param temps Integer
     * @param etape EtapeIG
     * @throws TempsIncorrectException TempsIncorrectException
     */
    public void modiftemps(Integer temps, EtapeIG etape) throws TempsIncorrectException {
        if (temps <= 0) {
            throw new TempsIncorrectException();
        } else {
            etape.setTemps(temps);
        }
    }

    /**
     * Fonction modification de l'ecarttemps des etapes.
     *
     * @param etemps Integer
     * @param etape  EtapeIG
     * @throws EcartTempsException EcartTempsIncorrectException
     */
    public void modifecarttemps(Integer etemps, EtapeIG etape) throws EcartTempsException {
        if (etemps <= 0 || etemps >= etape.getTemps()) {
            throw new EcartTempsException();
        } else {
            etape.setEcartTemps(etemps);
        }
    }
}

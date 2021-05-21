package twisk.mondeIG;

import twisk.ClientTwisk;
import twisk.TailleComposants;
import twisk.exceptions.TwiskException.*;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueIdentifiant;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;
import twisk.vues.Observateur;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/**
 * Classe MondeIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */
@SuppressWarnings("unused")
public class MondeIG extends SujetObserve implements Iterable<EtapeIG>, Observateur {

    private final ArrayList<EtapeIG> selectedEtape = new ArrayList<>(10);
    private final ArrayList<ArcIG> selectedArc = new ArrayList<>(10);
    private final PointDeControleIG[] pdcCrea = new PointDeControleIG[2];
    private final ArrayList<ArcIG> arc = new ArrayList<>(10);
    private final HashMap<String, EtapeIG> etape = new HashMap<>();
    private final ArrayList<EtapeIG> etapeEntre = new ArrayList<>(5);
    private final ArrayList<EtapeIG> etapeSortie = new ArrayList<>(5);
    private final FabriqueIdentifiant fabriqueID = FabriqueIdentifiant.getInstance();
    private final TailleComposants composants = TailleComposants.getInstance();
    private CorrespondanceEtapes correspEtape;
    private Object simulation;
    private boolean simulationStart;

    /**
     * Fonction ajout d'activité.
     *
     * @param type String
     */
    public void ajouter(String type) {
        if (type.equals("Activite")) {
            String idf = fabriqueID.getIdentifiantEtape();
            ActiviteIG a = new ActiviteIG("Activite_" + idf, idf, composants.getVBoxLong(), composants.getVBoxLarg());
            etape.put(idf, a);
        } else if (type.equals("Guichet")) {
            String idf = fabriqueID.getIdentifiantEtape();
            GuichetIG a = new GuichetIG("Guichet_"+idf, idf, composants.getVBoxLong(), composants.getVBoxLarg(), 5);
            etape.put(idf, a);
        }
        notifierObservateur();
    }


    private Monde creerMonde(){
        correspEtape = new CorrespondanceEtapes();
        Monde m = new Monde();
        for(EtapeIG e: etape.values()){
            if(e.estUneActiviteRestreinte()){
                Etape actres = new ActiviteRestreinte(e.getNom(), ((ActiviteIG) e).getTemps(), ((ActiviteIG) e).getEcartTemps());
                m.ajouter(actres);
                correspEtape.ajouter(e, actres);
            } else if (e.estUnGuichet()){
                Etape guichet = new Guichet(e.getNom(), ((GuichetIG) e).getNbJetons());
                m.ajouter(guichet);
                correspEtape.ajouter(e, guichet);
            } else {
                Etape act = new Activite(e.getNom(), ((ActiviteIG) e).getTemps(), ((ActiviteIG) e).getEcartTemps());
                m.ajouter(act);
                correspEtape.ajouter(e, act);
            }
        }

        for(EtapeIG eEntre : etapeEntre){
            m.aCommeEntree(correspEtape.get(eEntre));
        }
        for(EtapeIG e: etape.values()){
            for(EtapeIG suc : e.getSuccesseur()){
                correspEtape.get(e).ajouterSuccesseur(correspEtape.get(suc));
            }
        }
        for(EtapeIG eSortie : etapeSortie){
            m.aCommeSortie(correspEtape.get(eSortie));
        }

        return m;
    }



    /**
     * Fonction de simulation du mondeIG.
     */
    public void simuler() throws MondeException {
        for (ArcIG a : arc) {
            EtapeIG src = a.getPoint(0).getEtape();
            EtapeIG dest = a.getPoint(1).getEtape();
            src.ajouterSuccesseur(dest);
        }
        verifierMondeIG();

        Monde m = creerMonde();
        try {
            ClassLoaderPerso ClassLoader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> classSim = ClassLoader.loadClass("twisk.simulation.Simulation");
            Class<?> classSim1 = ClassLoader.loadClass("twisk.simulation.Simulation$1");
            simulation = classSim.getDeclaredConstructor().newInstance();
            Method msetNbClients = classSim.getDeclaredMethod("setNbClients", int.class);
            Method msimuler = classSim.getDeclaredMethod("simuler", twisk.monde.Monde.class);
            msetNbClients.invoke(simulation, 5);
            msimuler.invoke(simulation, m);
            simulationStart = true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction de vérification de la validitée du monde.
     *
     * @throws MondeException Quand le monde n'est pas correct.
     */
    private void verifierMondeIG() throws MondeException {
        // Vérifie que le monde contient au moins une étape, une entré et une sortie.
        if(etapeEntre.size() < 1 || etape.size() < 1 || etapeSortie.size() < 1){
            throw new MondeException();
        }
        for(EtapeIG e : etape.values()){
            for(EtapeIG succ : e.getSuccesseur()){
                // Met l'activité suivant guichet en tant qu'activité restrainte.
                if(succ.estUneActivite() && e.estUnGuichet()){
                    ((ActiviteIG) succ).setEstUnActiviteRestrainte();
                }
                // Vérification qu'un guichet n'est pas suivis d'un guichet
                if(succ.estUnGuichet() && e.estUnGuichet()){
                    throw new MondeException();
                }
                // Tout les activités ont au moins un successeurs !
                if(e.getSuccesseur().size() < 1){
                    throw new MondeException();
                }
            }
        }
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
            if (e.getPoint(1).getPosX() == pt1.getPosX() && e.getPoint(1).getPosY() == pt1.getPosY() ||
                            e.getPoint(0).getPosX() == pt2.getPosX() && e.getPoint(0).getPosY() == pt2.getPosY()) {
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

    /**
     * Fonction création des arc
     *
     * @param p un point de controle.
     * @throws AlreadyExistException Si le point existe déjà
     * @throws SamePointException Si l'arc se crée sur le même points
     */
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
        int i = 0;
        while (i < getSelectedArc().size() && !isIn) {
            if (getSelectedArc().get(i) == arcparam) {
                isIn = true;
            }
            i++;
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
        int i = 0;
        while (i < this.getSelectedEtape().size() && !isIn) {
            if (getSelectedEtape().get(i).getIdentifiant().equals(etapeparam.getIdentifiant())) {
                isIn = true;
            }
            i++;
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
        int i = 0;
        for (EtapeIG e : getSelectedEtape()) {
            while (i < getEtapeEntre().size() && !flag) {
                if (e == getEtape().get(String.valueOf(i))) {
                    flag = true;
                }
                i++;
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
        for (EtapeIG e : getSelectedEtape()) {
            e.setSelectionne();
        }
        getSelectedEtape().clear();
        this.notifierObservateur();
    }


    /**
     * Gestion des sortis dans le monde
     */
    public void gestionSortie() {
        boolean flag = false;
        int i = 0;
        for (EtapeIG e : getSelectedEtape()) {
            while (i < getEtapeSortie().size() && !flag) {
                if (e == getEtapeSortie().get(i)) {
                    flag = true;
                    this.selectedArc.clear();
                }
                i++;
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
        for (EtapeIG e : getSelectedEtape()) {
            e.setSelectionne();
        }
        getSelectedEtape().clear();
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

    public CorrespondanceEtapes getCorrespEtape() {
        return correspEtape;
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
    public void modiftemps(Integer temps, ActiviteIG etape) throws TempsIncorrectException {
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
    public void modifecarttemps(Integer etemps, ActiviteIG etape) throws EcartTempsException {
        if (etemps <= 0 || etemps >= etape.getTemps()) {
            throw new EcartTempsException();
        } else {
            etape.setEcartTemps(etemps);
        }
    }

    /**
     * Fonction de modifation du nombre de jetons dans les guichets
     *
     * @param nb    int
     * @param etape GuichetIG
     */
    public void modifNbJetons(Integer nb, GuichetIG etape) {
        etape.setNbJetons(nb);
    }

    public ArrayList<Client> getClients() {
        GestionnaireClients gestionnaireClients = null;
        try {
            Method mgestionnaireclients = simulation.getClass().getDeclaredMethod("getGestionnaireClients");
            gestionnaireClients = (GestionnaireClients) mgestionnaireclients.invoke(simulation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(gestionnaireClients).getListeClient();
    }

    public boolean isSimulationStart() {
        return simulationStart;
    }

    @Override
    public void reagir() {
        notifierObservateur();
    }

}

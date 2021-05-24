package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import twisk.ecouteur.*;
import twisk.mondeIG.MondeIG;


/**
 * Classe VueMenu.
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueMenu extends MenuBar implements Observateur {

    private final MondeIG monde;
    private final MenuItem renameselec = new MenuItem("Renommer la sélection");
    private final Menu param = new Menu("Paramètres");
    private final MenuItem temps;
    private final MenuItem ecartTemps;
    private final MenuItem modifJeton;

    /**
     * Constructeur VueMenu.
     *
     * @param m MondeIG
     */
    public VueMenu(MondeIG m) {
        this.monde = m;
        m.ajouterObservateur(this);
        Menu fichier = new Menu("Fichier");
        Menu mmonde = new Menu("Monde");
        MenuItem entre = new MenuItem("Entrée");
        temps = new MenuItem("Temps");
        ecartTemps = new MenuItem("Ecart temps");
        ecartTemps.setOnAction(new EcouteurEcartTemps(monde));
        temps.setOnAction(new EcouteurTemps(monde));
        modifJeton = new MenuItem("Modifier le nombre de jeton");
        modifJeton.setOnAction(new EcouteurModifJetons(monde));
        param.setDisable(true);
        param.getItems().addAll(temps, ecartTemps, modifJeton);
        entre.setOnAction(new EcouteurEntree(monde));
        MenuItem sortie = new MenuItem("Sortie");
        sortie.setOnAction(new EcouteurSortie(monde));
        MenuItem quitter = new MenuItem("Quitter");
        mmonde.getItems().addAll(entre, sortie);
        quitter.setOnAction(e -> Platform.exit());
        fichier.getItems().add(quitter);
        Menu editions = new Menu("Edition");
        renameselec.setOnAction(new EcouteurRenommer(monde));
        renameselec.setDisable(true);
        MenuItem edtGuichetnom = new MenuItem("Modifier le nom du guichet");
        MenuItem suprSelec = new MenuItem("Supprimer la sélection");
        MenuItem suprArc = new MenuItem("Supprimer les arcs");
        MenuItem deselect = new MenuItem("Déselectionner");
        deselect.setOnAction(new EcouteurDeselection(monde));
        suprArc.setOnAction(new EcouteurSupprimerArc(monde));
        suprSelec.setOnAction(new EcouteurSupprimer(monde));
        editions.getItems().addAll(suprSelec, renameselec, suprArc, edtGuichetnom, deselect);
        Menu save = new Menu("Sauvegarde");
        MenuItem sauvegarder = new MenuItem("Sauvegarder");
        MenuItem deserialization = new MenuItem("Charger");
        sauvegarder.setOnAction(e -> m.serialization());
        deserialization.setOnAction(e -> m.deserialization());
        save.getItems().addAll(sauvegarder,deserialization);

        this.getMenus().addAll(fichier, editions, mmonde, param, save);
    }

    /**
     * Fonction réagir.
     */
    @Override
    public void reagir() {
        renameselec.setDisable(monde.getSelectedEtape().size() != 1);
        param.setDisable(monde.getSelectedEtape().size() != 1);
        if (monde.getSelectedEtape().size() == 1) {
            temps.setDisable(monde.getSelectedEtape().get(0).estUnGuichet());
            ecartTemps.setDisable(monde.getSelectedEtape().get(0).estUnGuichet());
            modifJeton.setDisable(monde.getSelectedEtape().get(0).estUneActivite());
        }
    }
}

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
        MenuItem temps = new MenuItem("Temps");
        MenuItem ecarttemps = new MenuItem("Ecart temps");
        ecarttemps.setOnAction(new EcouteurEcartTemps(monde));
        temps.setOnAction(new EcouteurTemps(monde));
        MenuItem modifjeton = new MenuItem("Modifier le nombre de jeton");
        param.setDisable(true);
        param.getItems().addAll(temps, ecarttemps, modifjeton);
        entre.setOnAction(new EcouteurEntree(monde));
        MenuItem sortis = new MenuItem("Sortie");
        sortis.setOnAction(new EcouteurSortie(monde));
        MenuItem quitter = new MenuItem("Quitter");
        mmonde.getItems().addAll(entre, sortis);
        quitter.setOnAction(e -> Platform.exit());
        fichier.getItems().add(quitter);
        Menu editions = new Menu("Edition");
        renameselec.setOnAction(new EcouteurRenommer(monde));
        renameselec.setDisable(true);
        MenuItem edtGuichetnom = new MenuItem("Modifier le nom du guichet");
        MenuItem suprselec = new MenuItem("Supprimer la sélection");
        MenuItem suprArc = new MenuItem("Supprimer les arcs");
        MenuItem deselect = new MenuItem("Déselectionner");
        deselect.setOnAction(new EcouteurDeselection(monde));
        suprArc.setOnAction(new EcouteurSupprimerArc(monde));
        suprselec.setOnAction(new EcouteurSupprimer(monde));
        editions.getItems().addAll(suprselec, renameselec, suprArc, edtGuichetnom, deselect);

        this.getMenus().addAll(fichier, editions, mmonde, param);
    }

    /**
     * Fonction réagir.
     */
    @Override
    public void reagir() {
        renameselec.setDisable(monde.getSelectedEtape().size() != 1);
        param.setDisable(monde.getSelectedEtape().size() != 1);
    }
}

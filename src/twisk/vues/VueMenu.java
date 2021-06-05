package twisk.vues;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import twisk.ecouteur.*;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * Classe VueMenu.
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueMenu extends MenuBar implements Observateur {

    private final MondeIG monde;
    private final MenuItem renameselec = new MenuItem("Renommer la sélection");
    private final Menu param = new Menu("Paramètres");
    private final Menu menuMonde = new Menu("Monde");
    private final Menu menuEdition = new Menu("Edition");
    private final Menu simulation = new Menu("Simulation");
    private final Menu save = new Menu("Sauvegarde");
    private final MenuItem temps;
    private final MenuItem ecartTemps;
    private final MenuItem modifJeton;
    private final MenuItem suprArc = new MenuItem("Supprimer les arcs");

    /**
     * Constructeur VueMenu.
     *
     * @param m MondeIG
     */
    public VueMenu(MondeIG m) {
        this.monde = m;
        m.ajouterObservateur(this);

        // Menu monde
        MenuItem entre = new MenuItem("Définir comme entrée");
        MenuItem sortie = new MenuItem("Définir comme sortie");
        entre.setOnAction(new EcouteurEntree(monde));
        sortie.setOnAction(new EcouteurSortie(monde));
        menuMonde.setDisable(true);
        menuMonde.getItems().addAll(entre, sortie);

        // Menu édition
        MenuItem suprSelec = new MenuItem("Supprimer la sélection");
        MenuItem deselect = new MenuItem("Déselectionner");
        renameselec.setOnAction(new EcouteurRenommer(monde));
        deselect.setOnAction(new EcouteurDeselection(monde));
        suprArc.setOnAction(new EcouteurSupprimerArc(monde));
        suprSelec.setOnAction(new EcouteurSupprimer(monde));
        renameselec.setDisable(true);
        menuEdition.setDisable(true);
        menuEdition.getItems().addAll(suprSelec, renameselec, suprArc, deselect);

        // Menu paramètres
        temps = new MenuItem("Temps");
        ecartTemps = new MenuItem("Ecart temps");
        modifJeton = new MenuItem("Nombre de jeton");
        temps.setOnAction(new EcouteurTemps(monde));
        ecartTemps.setOnAction(new EcouteurEcartTemps(monde));
        modifJeton.setOnAction(new EcouteurModifJetons(monde));
        param.setDisable(true);
        param.getItems().addAll(temps, ecartTemps, modifJeton);

        // Menu simulation
        MenuItem nbClient = new MenuItem("Choisir le nombre de client");
        MenuItem loiClient = new MenuItem("Choisir la loi d'arrivée des clients");
        nbClient.setDisable(true);
        loiClient.setDisable(true);
        /* À faire
        nbClient.setOnAction();
        loiClient.setOnAction();
        */
        simulation.getItems().addAll(nbClient, loiClient);

        // Menu de sauvegarde/Chargement.
        MenuItem sauvegarder = new MenuItem("Sauvegarder un monde");
        MenuItem charger = new MenuItem("Charger un monde");
        Menu mondepre = new Menu("Monde prédéfinis");
        MenuItem boulangerie = new MenuItem("Boulangerie");
        boulangerie.setOnAction(e -> {
            try {
                monde.deserialisation(new File(Objects.requireNonNull(getClass().getResource("/twisk/ressources/mondePredef/boulangerie.ser")).toURI()));
            } catch (URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
        MenuItem zoo = new MenuItem("Zoo");
        zoo.setOnAction(e -> {
            try {
                monde.deserialisation(new File(Objects.requireNonNull(getClass().getResource("/twisk/ressources/mondePredef/zoo.ser")).toURI()));
            } catch (URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
        MenuItem bifurcator = new MenuItem("Bifurcator");
        bifurcator.setOnAction(e-> {
            try {
                monde.deserialisation(new File(Objects.requireNonNull(getClass().getResource("/twisk/ressources/mondePredef/bifurcator.ser")).toURI()));
            } catch (URISyntaxException ioexception) {
                ioexception.printStackTrace();
            }
        });
        mondepre.getItems().addAll(boulangerie, zoo, bifurcator);
        sauvegarder.setOnAction(e -> {
            DirectoryChooser dir = new DirectoryChooser();
            File pathtodir = dir.showDialog(this.getScene().getWindow());
            if (pathtodir != null) {
                final Stage dialog = new Stage();
                dialog.setTitle("Nom de la sauvegarde");
                Button valider = new Button("Valider");
                Label label = new Label("Nom du fichier : ");
                TextField saisie = new TextField();

                dialog.initModality(Modality.NONE);
                VBox dialogVbox = new VBox(20);
                dialogVbox.setPadding(new Insets(20,20,20,20));
                dialogVbox.setAlignment(Pos.CENTER);
                dialogVbox.getChildren().addAll(label, saisie, valider);
                valider.setOnAction(f -> {
                    m.serialization(pathtodir.getAbsolutePath() + "/" + saisie.getText() + ".ser");
                    dialog.close();
                });
                Scene dialogScene = new Scene(dialogVbox, 300, 125);
                dialog.setScene(dialogScene);
                dialog.setResizable(false);
                dialog.show();
            }
        });
        charger.setOnAction(e -> {
            final Stage dialog = new Stage();
            dialog.setTitle("Changement des dates");
            FileChooser choixfichier = new FileChooser();
            choixfichier.setTitle("Quel fichier charger ?");
            choixfichier.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ser", "*.ser"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File selectedFile = choixfichier.showOpenDialog(dialog);
            if (selectedFile != null) {
                monde.deserialisation(selectedFile);
            }
        });
        save.getItems().addAll(sauvegarder, charger, mondepre);

        //Menu Quitter
        Menu menuQuitter = new Menu("Quitter");
        MenuItem quitter = new MenuItem("Fermer l'application");
        quitter.setOnAction(e -> Platform.exit());
        quitter.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        menuQuitter.getItems().add(quitter);

        this.getMenus().addAll(menuMonde, menuEdition, param, simulation, save, menuQuitter);
    }

    /**
     * Fonction réagir.
     */
    @Override
    public void reagir() {
        save.setDisable(monde.isSimulationStart());
        simulation.setDisable(monde.isSimulationStart());
        renameselec.setDisable(monde.getSelectedEtape().size() != 1);
        param.setDisable(monde.getSelectedEtape().size() != 1);
        if (monde.getSelectedEtape().size() == 1) {
            temps.setDisable(monde.getSelectedEtape().get(0).estUnGuichet());
            ecartTemps.setDisable(monde.getSelectedEtape().get(0).estUnGuichet());
            modifJeton.setDisable(monde.getSelectedEtape().get(0).estUneActivite());
        }
        menuMonde.setDisable(monde.getSelectedEtape().size() == 0);
        for (EtapeIG e : monde.getSelectedEtape()) {
            menuMonde.setDisable(e.estUnGuichet());
        }
        menuEdition.setDisable(monde.getSelectedEtape().size() == 0);
        suprArc.setDisable(monde.getSelectedArc().size() == 0);
    }
}

package twisk.vues;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import twisk.ecouteur.*;
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
        MenuItem suprSelec = new MenuItem("Supprimer la sélection");
        MenuItem suprArc = new MenuItem("Supprimer les arcs");
        MenuItem deselect = new MenuItem("Déselectionner");
        deselect.setOnAction(new EcouteurDeselection(monde));
        suprArc.setOnAction(new EcouteurSupprimerArc(monde));
        suprSelec.setOnAction(new EcouteurSupprimer(monde));
        editions.getItems().addAll(suprSelec, renameselec, suprArc, deselect);
        Menu save = new Menu("Sauvegarde");
        MenuItem sauvegarder = new MenuItem("Sauvegarder");
        MenuItem charger = new MenuItem("Charger");
        Menu mondepre = new Menu("Monde");
        MenuItem boulangerie = new MenuItem("Boulangerie");
        boulangerie.setOnAction(e->{
            try {
                monde.deserialisation(new File(Objects.requireNonNull(getClass().getResource("/twisk/ressources/mondePredef/boulangerie.ser")).toURI()));
            } catch (URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
        MenuItem zoo = new MenuItem("Zoo");
        zoo.setOnAction( e-> {
            try {
                monde.deserialisation(new File(Objects.requireNonNull(getClass().getResource("/twisk/ressources/mondePredef/zoo.ser")).toURI()));
            } catch (URISyntaxException ioException){
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

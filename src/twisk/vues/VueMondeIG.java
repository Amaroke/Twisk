package twisk.vues;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import twisk.ecouteur.EcouteurDragDropped;
import twisk.ecouteur.EcouteurDragOver;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;
import twisk.simulation.Client;

import java.util.ArrayList;

/**
 * Classe VueMondeIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueMondeIG extends Pane implements Observateur {

    private final MondeIG m;

    /**
     * Constructeur VueMondeIG.
     *
     * @param monde MondeIG
     */
    public VueMondeIG(MondeIG monde) {
        this.m = monde;
        this.m.ajouterObservateur(this);
        for (int i = 0; i < m.nbEtapes(); i++) {
            EtapeIG ids = m.getEtape().get(String.valueOf(i));
            VueEtapeIG vue = new VueActiviteIG(m, ids);
            this.getChildren().add(vue);
        }
        this.setOnDragOver(new EcouteurDragOver());
        this.setOnDragDropped(new EcouteurDragDropped(m, this));
        reagir();
    }

    public void ajouterClient(HBox hbox, EtapeIG e) {
        if (m.getSimulation() != null) {
            if (m.isSimulationStart()) {
                for (Client c : m.getClients()) {
                    if (m.getCorrespEtape().get(e).equals(c.getEtape())) {
                        VueClientIG v = new VueClientIG(c);
                        hbox.getChildren().add(v);
                    }
                }
            }
        }
    }

    public void ajouterClientGuichet(ArrayList<HBox> hbox, EtapeIG e) {
        if (m.getSimulation() != null) {
            if (m.isSimulationStart()) {
                for (Client c : m.getClients()) {
                    if (m.getCorrespEtape().get(e).equals(c.getEtape())) {
                        VueClientIG v = new VueClientIG(c);
                        if (!e.estUnGuichet()) {
                            for (HBox hb : hbox) {
                                if (hb.getChildren().isEmpty()) {
                                    hb.getChildren().add(v);
                                }
                            }
                        } else {
                            if (((GuichetIG) e).getSensSortie() == 0) {
                                for (HBox hBox : hbox) {
                                    if (hBox.getChildren().isEmpty()) {
                                        hBox.getChildren().add(v);
                                    }
                                }
                            }
                            else{
                                for (int i = hbox.size()-1; i >= 0; --i) {
                                    if (hbox.get(i).getChildren().isEmpty()) {
                                        hbox.get(i).getChildren().add(v);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Fonction réaction/création des arc + pdc.
     */
    @Override
    public void reagir() {
        Runnable command = () -> {
            this.getChildren().clear();
            for (int i = 0; i < m.getArc().size(); i++) {
                VueArcIG a = new VueArcIG(m.getArc().get(i), m);
                this.getChildren().add(a);
            }
            for (EtapeIG e : m) {
                if (e.estUneActivite()) {
                    VueActiviteIG vue = new VueActiviteIG(m, e);
                    ajouterClient(vue.getHbox(), e);
                    this.getChildren().add(vue);
                } else if (e.estUnGuichet()) {
                    VueGuichetIG vue = new VueGuichetIG(m, e);
                    ajouterClientGuichet(vue.getHbox(), e);
                    this.getChildren().add(vue);
                }
                if (m.getSimulation() == null || !m.isSimulationStart()) {
                    if (!e.estUnGuichet()) {
                        for (int j = 0; j < 4; j++) {
                            VuePointDeControleIG pdc = new VuePointDeControleIG(m, e.getPdc(j));
                            this.getChildren().add(pdc);
                        }
                    } else {
                        for (int j = 0; j < 2; j++) {
                            VuePointDeControleIG pdc = new VuePointDeControleIG(m, e.getPdc(j));
                            this.getChildren().add(pdc);
                        }
                    }
                }
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}

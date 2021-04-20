package twisk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

public class MainTwisk extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MondeIG monde = new MondeIG();
        VueMondeIG vm = new VueMondeIG(monde);
        VueOutils voutils = new VueOutils(monde);
        VueMenu menu = new VueMenu(monde);
        BorderPane root = new BorderPane();
        root.setTop(menu);
        root.setCenter(vm);
        root.setBottom(voutils);
        Scene scene = new Scene(root, 1000, 800);
        root.getStylesheets().add(getClass().getResource("style.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("/ressources/images/twiskimg.png"));
        primaryStage.setTitle("TwiskIG");
        primaryStage.show();
    }
}

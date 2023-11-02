package ca.qc.bdeb.inf203.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        var pagePrincipale = new PagePrincipale();

        var pageInfos = new PageInfos();

        var pageJeu=new PageJeu();

        var scene = new Scene(pagePrincipale.getPagePrincipale(),900,520);

        //set la root de la page jeu
        pagePrincipale.getBoutonInfos().setOnAction(event -> {
            scene.setRoot(pageInfos.getPageInfos());
        });

        pageInfos.getButtonRetour().setOnAction(event -> {
            scene.setRoot(pagePrincipale.getPagePrincipale());
        });

        pagePrincipale.getBoutonJouer().setOnAction(event -> {
            scene.setRoot(pageJeu);
        });


        //Appuyer sur ESCAPE quitte l'application
        scene.setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) Platform.exit();
        }));

        stage.setScene(scene);
        stage.setTitle("Charlotte la Barbotte");
        Image iconCharlotteLaBarbotte = new Image("charlotte.png");
        stage.getIcons().add(iconCharlotteLaBarbotte);
        stage.show();
    }
}
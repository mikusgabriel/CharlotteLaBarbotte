package ca.qc.bdeb.inf203.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        var pagePrincipale= new PagePrincipale();
        var pageInfos = new PageInfos();
        var pageJeu = new PageJeu();



        //Appuyer sur ESCAPE pour quitter l'application
        pagePrincipale.getScenePrincipale().setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) Platform.exit();
        }));

        pageInfos.getButtonRetour().setOnAction(event -> {
            stage.setScene(pagePrincipale.getScenePrincipale());
        });

        pagePrincipale.getBoutonJouer().setOnAction(event -> {
            stage.setScene(pageJeu.getSceneJeu());
        });

        stage.setScene(pagePrincipale.getScenePrincipale());
        stage.setTitle("Charlotte la Barbotte");
        Image iconCharlotteLaBarbotte = new Image("charlotte.png");
        stage.getIcons().add(iconCharlotteLaBarbotte);


        //Actions avec ESCAPE
        stage.getScene().setOnKeyPressed((event -> {

            if(event.getCode() == KeyCode.ESCAPE && stage.getScene() == pageInfos.getSceneInfos()) stage.setScene(pagePrincipale.getScenePrincipale());
            //else if(event.getCode() == KeyCode.ESCAPE && scene.getRoot() == pageJeu.getPageJeu()) scene.setRoot(pagePrincipale.getPagePrincipale());
            else if(event.getCode() == KeyCode.ESCAPE && stage.getScene() == pagePrincipale.getScenePrincipale()) Platform.exit();
        }));

        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil
        pageJeu.getSceneJeu().setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) stage.setScene(pagePrincipale.getScenePrincipale());
        }));



        pagePrincipale.getBoutonInfos().setOnAction(event -> stage.setScene(pageInfos.getSceneInfos())
        );
        pagePrincipale.getBoutonJouer().setOnAction(event -> stage.setScene(pageJeu.getSceneJeu()));
        pageInfos.getButtonRetour().setOnAction(event -> stage.setScene(pagePrincipale.getScenePrincipale()));

        stage.show();
    }
}
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
            scene.setRoot(pagePrincipale.getPagePrincipale());
        });

        pagePrincipale.getBoutonJouer().setOnAction(event -> {
            scene.setRoot(pageJeu.getPageJeu());
        });

        var timer = new AnimationTimer() {
            long lastTime = System.nanoTime();
            @Override
            public void handle(long now) {
                double deltaTemps = (now - lastTime) * 1e-9;
                partie.update(deltaTemps);
                partie.draw(context);
                lastTime = now;
            }
        };
        timer.start();


        //Actions avec ESCAPE
        scene.setOnKeyPressed((event -> {

            if(event.getCode() == KeyCode.ESCAPE && scene.getRoot() == pageInfos.getPageInfos()) scene.setRoot(pagePrincipale.getPagePrincipale());
            //else if(event.getCode() == KeyCode.ESCAPE && scene.getRoot() == pageJeu.getPageJeu()) scene.setRoot(pagePrincipale.getPagePrincipale());
            else if(event.getCode() == KeyCode.ESCAPE && scene.getRoot() == pagePrincipale.getPagePrincipale()) Platform.exit();
        }));

        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil
        pageJeu.getSceneJeu().setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) stage.setScene(pagePrincipale.getScenePrincipale());
        }));

        stage.setScene(pagePrincipale.getScenePrincipale());
        stage.setTitle("Charlotte la Barbotte");
        Image iconCharlotteLaBarbotte = new Image("charlotte.png");
        stage.getIcons().add(iconCharlotteLaBarbotte);

        pagePrincipale.getBoutonInfos().setOnAction(event -> stage.setScene(pageInfos.getSceneInfos())
        );
        pagePrincipale.getBoutonJouer().setOnAction(event -> stage.setScene(pageJeu.getSceneJeu()));
        pageInfos.getButtonRetour().setOnAction(event -> stage.setScene(pagePrincipale.getScenePrincipale()));

        stage.show();
    }
}
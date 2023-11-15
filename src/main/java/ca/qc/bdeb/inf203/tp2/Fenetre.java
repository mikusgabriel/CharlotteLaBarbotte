package ca.qc.bdeb.inf203.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Fenetre extends Application {
    @Override
    public void start(Stage stage) {
        var pagePrincipale= new PagePrincipale();
        var pageInfos = new PageInfos();
        var pageJeu = new PageJeu();

        //Appuyer sur ESCAPE pour quitter l'application
        pagePrincipale.getScenePrincipale().setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) Platform.exit();
        }));

        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil a partir de la page info
        pageInfos.getSceneInfos().setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) stage.setScene((pagePrincipale.getScenePrincipale()));
            else{
                System.out.println("key pressed");
                Input.setKeyPressed(event.getCode(), true);
            }
        }));

        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil a partir de la page jeu
        pageJeu.getSceneJeu().setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) stage.setScene(pagePrincipale.getScenePrincipale());
            else{
                System.out.println("key pressed");
                Input.setKeyPressed(event.getCode(), true);
            }
        }));

        // FIXME Ca sert a quoi au juste?? -sam
        ////////Je ne sais pas si cest la bonne place pour le mettre ici (setkeypressed en haut aussi)
        pageJeu.getSceneJeu().setOnKeyReleased((e) -> {
            System.out.println("keyreleased");
            Input.setKeyPressed(e.getCode(), false);
        });

        //Bouton retourner sur la page d'accueil
        pageInfos.getButtonRetour().setOnAction(event ->
            stage.setScene(pagePrincipale.getScenePrincipale())
        );

        //Bouton aller a la page infos
        pagePrincipale.getBoutonInfos().setOnAction(event ->
                stage.setScene(pageInfos.getSceneInfos()));

        //Bouton aller a la page jeu
        pagePrincipale.getBoutonJouer().setOnAction(event ->
                stage.setScene(pageJeu.getSceneJeu()));

        stage.setScene(pagePrincipale.getScenePrincipale());
        stage.setTitle("Charlotte la Barbotte");
        stage.getIcons().add(new Image("charlotte.png"));
        stage.show();
    }
}

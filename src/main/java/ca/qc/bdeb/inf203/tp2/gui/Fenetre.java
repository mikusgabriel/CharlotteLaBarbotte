package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.utils.Input;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Fenetre extends Application {
    public static final int HAUTEUR = 520, LARGEUR = 900;
    @Override
    public void start(Stage stage) {
        var pagePrincipale = new PagePrincipale();
        var pageInfos = new PageInfos();
        var pageJeu = new PageJeu();

        var scenePrincipale = new Scene(pagePrincipale.getRoot(), LARGEUR, HAUTEUR);
        var sceneInfos = new Scene(pageInfos.getRoot(), LARGEUR, HAUTEUR);
        var sceneJeu = new Scene(pageJeu.getRoot(), LARGEUR, HAUTEUR);

        //--------KEYBOARD EVENTS--------
        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil a partir de la page info
        sceneInfos.getRoot().setOnKeyPressed((event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(scenePrincipale);
            }
        }));

        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil a partir de la page jeu
        sceneJeu.setOnKeyPressed((event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(scenePrincipale);
                pageJeu.getTimer().stop();
            }
            //debug mode
            else if (event.getCode() == KeyCode.D) {
                pageJeu.setDebugMode(true);
                System.out.println("debug mode");
            }
            //Detecter lorsqu'une touche est appuyee
            else {
                Input.setKeyPressed(event.getCode(), true);
            }
        }));

        //Detecter lorsqu'une touche est relachee
        sceneJeu.setOnKeyReleased((event ->
                Input.setKeyPressed(event.getCode(), false)));

        //--------BUTTON EVENTS--------
        //Bouton retourner sur la page d'accueil a partir de la page infos
        pageInfos.getButtonRetour().setOnAction(event ->
                stage.setScene(scenePrincipale)
        );

        //Bouton aller a la page infos a partir de la page d'accueil
        pagePrincipale.getBoutonInfos().setOnAction(event ->
                stage.setScene(sceneInfos)
        );

        //Bouton aller a la page jeu a partir de la page d'accueil
        pagePrincipale.getBoutonJouer().setOnAction(event ->
        {
            stage.setScene(sceneJeu);
            pageJeu.getTimer().start();
        }
        );

        stage.setScene(scenePrincipale);
        stage.setResizable(false);
        stage.setTitle("Charlotte la Barbotte");
        stage.getIcons().add(new Image("charlotte.png"));
        stage.show();
    }
}

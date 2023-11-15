package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.utils.Input;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Fenetre extends Application {

    @Override
    public void start(Stage stage) {
        var hauteurFenetre = 520;
        var largeurFenetre = 900;
        var pagePrincipale= new PagePrincipale();
        var pageInfos = new PageInfos();
        var pageJeu = new PageJeu(hauteurFenetre, largeurFenetre);

        var scene = new Scene(pagePrincipale.getRoot(), largeurFenetre, hauteurFenetre);

        //--------KEYBOARD EVENTS--------
        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil a partir de la page info
        pageInfos.getRoot().setOnKeyPressed((event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                scene.setRoot(pagePrincipale.getRoot());
            }
        }));

        //Appuyer sur ESCAPE pour retourner à l'écran d'accueil a partir de la page jeu
        scene.setOnKeyPressed((event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                scene.setRoot(pagePrincipale.getRoot());
            }
            //Detecter lorsqu'une touche est appuyee
            else {
                Input.setKeyPressed(event.getCode(), true);
            }
        }));

        //Detecter lorsqu'une touche est relachee
        scene.setOnKeyReleased((event ->
                Input.setKeyPressed(event.getCode(), false)));

        //--------BUTTON EVENTS--------
        //Bouton retourner sur la page d'accueil a partir de la page infos
        pageInfos.getButtonRetour().setOnAction(event ->
                scene.setRoot(pagePrincipale.getRoot())
        );

        //Bouton aller a la page infos a partir de la page d'accueil
        pagePrincipale.getBoutonInfos().setOnAction(event ->
                scene.setRoot(pageInfos.getRoot())
        );

        //Bouton commencer une partie
        pagePrincipale.getBoutonJouer().setOnAction(event ->
                scene.setRoot(pageJeu.getRoot())
        );

        stage.setScene(scene);
        stage.setTitle("Charlotte la Barbotte");
        stage.getIcons().add(new Image("charlotte.png"));
        stage.show();
    }
}

package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.utils.Input;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Dans la classe Fenetre, on crée les différentes scènes et
 * on attribue les fonctionnalités nécessaires aux boutons
 * et touches du clavier
 */
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
        sceneInfos.getRoot().setOnKeyPressed((event -> {
            // Appuyer sur ESCAPE pour
            // retourner à l'écran d'accueil
            // à partir de la page info
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(scenePrincipale);
            }
        }));
        sceneJeu.setOnKeyPressed(event -> {
            // Appuyer sur ESCAPE pour
            // retourner à l'écran d'accueil
            // à partir de la page jeu
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(scenePrincipale);
                pageJeu.getTimer().stop();
            }
            else {
                // Détecter lorsqu'une touche est appuyée
                Input.setKeyPressed(event.getCode(), true);
            }
        });
        sceneJeu.setOnKeyReleased((event ->
                //Détecter lorsqu'une touche est relâchée
                Input.setKeyPressed(event.getCode(), false)));

        //--------BUTTON EVENTS--------
        // Bouton retourner sur la page
        // d'accueil à partir de la page
        // infos
        pageInfos.getButtonRetour().setOnAction(event ->
                stage.setScene(scenePrincipale)
        );

        // Bouton aller a la page infos
        // à partir de la page d'accueil
        pagePrincipale.getBoutonInfos().setOnAction(event ->
                stage.setScene(sceneInfos)
        );

        // Bouton aller a la page jeu
        // à partir de la page d'accueil
        pagePrincipale.getBoutonJouer().setOnAction(event ->
        {
            sceneJeu.setRoot(new PageJeu().getRoot());
            stage.setScene(sceneJeu);
        });

        stage.setScene(scenePrincipale);
        stage.setResizable(false);
        stage.setTitle("Charlotte la Barbotte");
        stage.getIcons().add(new Image("charlotte.png"));
        stage.show();
    }
}

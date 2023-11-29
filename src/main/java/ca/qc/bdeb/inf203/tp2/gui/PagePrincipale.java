package ca.qc.bdeb.inf203.tp2.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Page d'accueil
 */
public class PagePrincipale {
    private final Button boutonJouer = new Button("Jouer!");
    private final Button boutonInfo = new Button("Infos");
    private final VBox root = new VBox();

    /**
     * Constructeur de la page d'accueil
     */
    public PagePrincipale(){
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10);

        var boutonsPagePrincipale=new HBox();
        boutonsPagePrincipale.setAlignment(Pos.CENTER);
        boutonsPagePrincipale.setSpacing(10);
        boutonsPagePrincipale.getChildren().addAll(boutonJouer,boutonInfo);

        var logo=new ImageView("logo.png");
        logo.setFitHeight(450);
        logo.setFitWidth(450);

        root.getChildren().addAll(logo,boutonsPagePrincipale);
        Background arrierePlan = Background.fill(Color.valueOf("#2A7FFF"));
        root.setBackground(arrierePlan);

        //Appuyer sur ESCAPE pour quitter l'application
        root.setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ESCAPE) Platform.exit();
        }));
    }

    //--------GETTERS--------
    public Button getBoutonJouer() {
        return boutonJouer;
    }
    public Button getBoutonInfos() {
        return boutonInfo;
    }
    public VBox getRoot() {
        return root;
    }
}

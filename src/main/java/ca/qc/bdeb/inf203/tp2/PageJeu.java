package ca.qc.bdeb.inf203.tp2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

public class PageJeu {

    private final VBox pageJeu = new VBox();
    Canvas canvas=new Canvas();
    private Scene sceneJeu = new Scene(new VBox(), 900, 520);

    PageJeu(){
        pageJeu.setAlignment(Pos.TOP_CENTER);
        pageJeu.setSpacing(10);

        var boutonsPagePrincipale=new HBox();



        var logo=new ImageView("logo.png");
        logo.setFitHeight(450);
        logo.setFitWidth(450);

        pageJeu.getChildren().addAll(logo,boutonsPagePrincipale);

    }


    private void choisirCouleurRandom(){

    }

    //--------GETTERS--------
    public Scene getSceneJeu() {
        return sceneJeu;
    }
}

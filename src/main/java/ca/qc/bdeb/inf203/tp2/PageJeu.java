package ca.qc.bdeb.inf203.tp2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PageJeu {
    private final Button boutonJouer=new Button("Jouer!");

    private final Button boutonInfo=new Button("Infos");

    private final VBox pageJeu =new VBox();

    PageJeu(){
        pageJeu.setAlignment(Pos.TOP_CENTER);
        pageJeu.setSpacing(10);

        var boutonsPagePrincipale=new HBox();
        boutonsPagePrincipale.setAlignment(Pos.CENTER);
        boutonsPagePrincipale.setSpacing(10);
        boutonsPagePrincipale.getChildren().addAll(boutonJouer,boutonInfo);

        var logo=new ImageView("logo.png");
        logo.setFitHeight(450);
        logo.setFitWidth(450);

        pageJeu.getChildren().addAll(logo,boutonsPagePrincipale);

    }


    private void choisirCouleurRandom(){

    }

}

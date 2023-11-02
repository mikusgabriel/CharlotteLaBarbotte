package ca.qc.bdeb.inf203.tp2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PagePrincipale extends Parent {

    private final Button boutonJouer=new Button("Jouer!");
    private final Button boutonInfo=new Button("Infos");

    VBox PagePrincipale(){
        VBox pagePrincipale=new VBox();
        pagePrincipale.setAlignment(Pos.TOP_CENTER);
        pagePrincipale.setSpacing(10);
        HBox boutonsPagePrincipale=new HBox();
        boutonsPagePrincipale.setAlignment(Pos.CENTER);
        boutonsPagePrincipale.setSpacing(10);
        boutonsPagePrincipale.getChildren().addAll(boutonJouer,boutonInfo);
        ImageView logo=new ImageView("logo.png");
        logo.setFitHeight(450);
        logo.setFitWidth(450);
        pagePrincipale.getChildren().addAll(logo,boutonsPagePrincipale);
        pagePrincipale.setBackground(Background.fill(Color.valueOf("#2A7FFF")));
        return pagePrincipale;
    }

    public Button getBoutonJouer() {
        return boutonJouer;
    }

    public Button getBoutonInfo() {
        return boutonInfo;
    }
}

package ca.qc.bdeb.inf203.tp2;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageInfos extends Parent {
    private final Text titreDeveloppeurs = new Text();
    private final Text informations = new Text();
    private final Button buttonRetour = new Button();
    private final Background arrierePlan = Background.fill(Color.valueOf("#2A7FFF"));

    PageInfos(){
        Text titreJeu = new Text("Charlotte la Barbotte"),
                par = new Text("par"),
                et = new Text("et"),
                dev1 = new Text("Gabriel Mikus"),
                dev2 = new Text("Samuel Leuchtmann");


        titreJeu.setFont(Font.font(30));

        var imageCharlotte = new ImageView();
        imageCharlotte.setImage(new Image("poisson1.png"));


        var devBox1 = new HBox();
        devBox1.getChildren().add(new Text(""))



    }

}

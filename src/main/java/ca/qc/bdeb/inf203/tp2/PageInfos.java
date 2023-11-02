package ca.qc.bdeb.inf203.tp2;

import javafx.geometry.Pos;
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
    private final VBox pageInfos = new VBox();

    PageInfos(){
        Text titreJeu = new Text("Charlotte la Barbotte"),
                par = new Text("par"),
                et = new Text("et"),
                dev1 = new Text("Gabriel Mikus"),
                dev2 = new Text("Samuel Leuchtmann"),
                informations = new Text("Travail remis à Nicolas Hurtubise et Georges Côté. Graphismes adaptés de https://games-icons.net/ et de https://openclipart.org/. Développé dans le cadre du cours 420-203-RE - Développement de programmes dans un environnement graphique, au Collège de Bois-de-Boulogne.");
        titreJeu.setFont(Font.font(30));
        par.setFont(Font.font(15));
        et.setFont(Font.font(15));
        dev1.setFont(Font.font(20));
        dev2.setFont(Font.font(20));

        var imgViewPoisson = new ImageView(new Image("poisson1.png"));

        HBox hBox1 = new HBox(), hBox2 = new HBox();
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(par, dev1);
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(et, dev2);

        var buttonRetour = new Button("Retour");

        pageInfos.getChildren().addAll(titreJeu, imgViewPoisson, hBox1, hBox2, informations, buttonRetour);
        pageInfos.setAlignment(Pos.CENTER);
    }

    //Retourne la vbox
    public VBox getPageInfos() {
        return pageInfos;
    }
}

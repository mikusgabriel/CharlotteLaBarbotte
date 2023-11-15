package ca.qc.bdeb.inf203.tp2.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageInfos {
    private final Button buttonRetour = new Button();
    private final VBox root = new VBox();

    /**
     * Constructeur du UI de la page infos
     */
    PageInfos(){
        Text titreJeu = new Text("Charlotte la Barbotte"),
                par = new Text("Par"),
                et = new Text("et"),
                dev1 = new Text("Gabriel Mikus"),
                dev2 = new Text("Samuel Leuchtmann"),
                informations = new Text("Travail remis à Nicolas Hurtubise et Georges Côté. Graphismes adaptés de https://games-icons.net/ et de \nhttps://openclipart.org/. Développé dans le cadre du cours 420-203-RE - Développement de programmes \ndans un environnement graphique, au Collège de Bois-de-Boulogne.");
        titreJeu.setFont(Font.font(50));
        par.setFont(Font.font(30));
        et.setFont(Font.font(30));
        dev1.setFont(Font.font(45));
        dev2.setFont(Font.font(45));

        var imgViewPoisson = new ImageView(new Image("poisson1.png"));

        HBox hBox1 = new HBox(), hBox2 = new HBox();
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(par, dev1);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(et, dev2);
        hBox2.setAlignment(Pos.CENTER);

        buttonRetour.setText("Retour");

        root.getChildren().addAll(titreJeu, imgViewPoisson, hBox1, hBox2, informations, buttonRetour);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setBackground(Background.fill(Color.valueOf("#2A7FFF")));

    }

    //--------GETTERS--------
    public VBox getRoot() {
        return root;
    }
    public Button getButtonRetour() {
        return buttonRetour;
    }
}

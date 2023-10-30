package ca.qc.bdeb.inf203.tp2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        PagePrincipale pagePrincipale=new PagePrincipale();

        Scene scene=new Scene(pagePrincipale.PagePrincipale(),900,520);

        //set la root de la page jeu
        pagePrincipale.getBoutonInfo().setOnAction(event -> {
        });




        stage.setScene(scene);
        stage.setTitle("Charlotte la Barbotte");
        Image iconCharlotteLaBarbotte=new Image("charlotte.png");
        stage.getIcons().add(iconCharlotteLaBarbotte);





        stage.show();
    }
}
package ca.qc.bdeb.inf203.tp2;


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

public class PageJeu {

    private final VBox pageJeu = new VBox();
    Charlotte charlotte=new Charlotte();



    private Scene sceneJeu = new Scene(pageJeu, 900, 520);

    Canvas canvas=new Canvas(sceneJeu.getWidth(),sceneJeu.getHeight());

    PageJeu(){
        GraphicsContext context= canvas.getGraphicsContext2D();
        Partie partie = new Partie();
        var timer = new AnimationTimer() {
            long lastTime = System.nanoTime();
            @Override
            public void handle(long now) {
                double deltaTemps = (now - lastTime) * 1e-9;
                partie.update(deltaTemps);
                partie.draw(context);
                lastTime = now;
            }
        };
        timer.start();
        pageJeu.getChildren().add(canvas);

    }


    private void choisirCouleurRandom(){

    }

    //--------GETTERS--------
    public Scene getSceneJeu() {
        return sceneJeu;
    }
}

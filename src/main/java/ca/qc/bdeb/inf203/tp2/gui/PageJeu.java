package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import ca.qc.bdeb.inf203.tp2.gameObjects.Decor;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class PageJeu {
    private final VBox root = new VBox();
    private final Canvas canvas = new Canvas();

    private final Camera camera= new Camera(0,0,900,520);

    PageJeu(int hauteurFenetre, int largeurFenetre){
        canvas.setHeight(hauteurFenetre);
        canvas.setWidth(largeurFenetre);

        var charlotte = new Charlotte(canvas);
        var backgroundColor = randomBackgroundColor();
        var partie = new Partie(canvas, charlotte,camera, backgroundColor);

        var timer = new AnimationTimer() {
            long lastTime = System.nanoTime();
            @Override
            public void handle(long now) {
                var context = canvas.getGraphicsContext2D();
                double deltaTemps = (now - lastTime) * 1e-9;

                partie.update(deltaTemps);
                context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                

                partie.draw(context);
                lastTime = now;
            }
        };
        timer.start();
        root.getChildren().add(canvas);
        root.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
    }

    /**
     * Créer une couleur random pour le background du monde
     * @return une classe Color
     */
    private Color randomBackgroundColor() {
        var rand = new Random();
        double hue = rand.nextDouble(190, 270), saturation = 0.84, brightness = 1.0;

        return Color.hsb(hue, saturation, brightness);
    }

    //--------GETTERS--------
    public VBox getRoot() {
        return root;
    }

}

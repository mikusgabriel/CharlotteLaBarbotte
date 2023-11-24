package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import ca.qc.bdeb.inf203.tp2.gameObjects.Decor;
import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PageJeu {
    private final static int SCREEN_HEIGHT = 520;
    private final static int SCREEN_WIDTH = 900;
    private final static int WORLD_WIDTH = 4160;
    private final static int WORLD_HEIGHT = 520;
    private final Pane root = new Pane();
    private final Canvas canvas = new Canvas();

    PageJeu(){
        canvas.setHeight(WORLD_HEIGHT);
        canvas.setWidth(WORLD_WIDTH);

        var partie = new Partie(canvas);
        var camera = new Camera();
        var decor = new Decor();

        var timer = new AnimationTimer() {
            long lastTime = System.nanoTime();
            @Override
            public void handle(long now) {
                var context = canvas.getGraphicsContext2D();
                double deltaTemps = (now - lastTime) * 1e-9;

                context.clearRect(0, 0, WORLD_WIDTH, WORLD_HEIGHT);

                // -- Update --
                partie.update(deltaTemps);
                camera.suivre(partie.getCharlotte());

                // -- Dessins --
                draw(context, camera, partie);

                lastTime = now;
            }
        };
        timer.start();
        root.getChildren().add(canvas);
        root.setBackground(decor.getBackground());
    }
    public void draw(GraphicsContext context, Camera camera, Partie partie) {
        context.setFill(Color.BLACK);

        double xEcran = camera.calculerEcranX(partie.getCharlotte().getX());

        context.fillRect(xEcran, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        partie.draw(context, camera);
    }







    //--------GETTERS--------
    public Pane getRoot() {
        return root;
    }

}

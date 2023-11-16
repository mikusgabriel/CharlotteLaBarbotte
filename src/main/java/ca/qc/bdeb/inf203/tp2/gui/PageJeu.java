package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import ca.qc.bdeb.inf203.tp2.gameObjects.Decor;
import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;

public class PageJeu {
    private final VBox root = new VBox();
    private final Canvas canvas = new Canvas();

    PageJeu(int hauteurFenetre, int largeurFenetre){
        canvas.setHeight(hauteurFenetre);
        canvas.setWidth(largeurFenetre);

        Partie partie = new Partie(canvas);

        var decor = new Decor();
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
        root.setBackground(decor.getBackground());
    }

    //--------GETTERS--------
    public VBox getRoot() {
        return root;
    }
}

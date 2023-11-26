package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.animation.AnimationTimer;

import javafx.scene.layout.*;

public class PageJeu {
    private final Pane root = new Pane();
    private final AnimationTimer timer;

    /**
     * Constructeur : Creer une nouvelle partie et commence un AnimationTimer
     */
    PageJeu(){
        var partie = new Partie(1);

        timer = new AnimationTimer() {
            long lastTime = System.nanoTime();
                @Override
                public void handle(long now) {
                    var context = partie.getCanvas().getGraphicsContext2D();
                    double deltaTemps = (now - lastTime) * 1e-9;

                    partie.update(deltaTemps);
                    context.clearRect(0, 0, partie.getCanvas().getWidth(), partie.getCanvas().getHeight());

                    partie.draw(context);
                    lastTime = now;
                }
            };

        root.getChildren().add(partie.getCanvas());
        root.setBackground(new Background(new BackgroundFill(partie.getBackgroundColor(), null, null)));
    }

    //--------GETTERS--------
    public Pane getRoot() {
        return root;
    }

    public AnimationTimer getTimer() {
        return timer;
    }
}

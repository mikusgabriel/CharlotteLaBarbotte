package ca.qc.bdeb.inf203.tp2.gui;

import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.animation.AnimationTimer;

import javafx.scene.layout.*;

/**
 * Page de jeu
 */
public class PageJeu {
    private final Pane root = new Pane();
    private final AnimationTimer timer;
    private boolean debugMode;

    /**
     * Constructeur : lorsqu'on va sur la page jeu, on crée une nouvelle partie et on commence le AnimationTimer
     */
    public PageJeu(){
        var partie = new Partie();


        this.timer = new AnimationTimer() {
            long lastTime = System.nanoTime();
                @Override
                public void handle(long now) {
                    var context = partie.getCanvas().getGraphicsContext2D();
                    double deltaTemps = (now - lastTime) * 1e-9;

                    context.clearRect(0, 0, partie.getCanvas().getWidth(), partie.getCanvas().getHeight());

                    partie.update(deltaTemps);

                    partie.draw(context);

                    if(partie.getCharlotte().isDead()) {
                        timer.stop();
                    }

                    lastTime = now;
                }
            };
        root.getChildren().add(partie.getCanvas());
        root.getChildren().add(partie.getAffichageNiveau());
        root.getChildren().add(partie.getMenuDebug());
        timer.start();
    }

    //--------GETTERS--------
    public Pane getRoot() {
        return root;
    }
    public AnimationTimer getTimer() {
        return timer;
    }

    //--------SETTERS--------
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}

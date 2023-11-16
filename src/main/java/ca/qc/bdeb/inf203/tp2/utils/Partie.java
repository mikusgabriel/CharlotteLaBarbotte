package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Partie {
    private final Charlotte charlotte;
    private final Canvas canvas;

    // Constructeur : on crée les objets de la partie
    public Partie(Canvas canvas, Charlotte charlotte) {
        this.charlotte = charlotte;
        this.canvas = canvas;
    }

    public void update(double deltaTemps) {
        charlotte.update(deltaTemps);
        charlotte.isDead();

// Tester les collisions
// Autres : vérifie si on a gagné/perdu, ...
    }

    public void draw(GraphicsContext context, Camera camera) {
        double height = canvas.getHeight();
        double width = canvas.getWidth();
        double xScreen = camera.calculerEcranX(width);

        charlotte.draw(context);
        context.setFill(Color.RED);
        context.fillRect(xScreen, height, width, height);
// Dessiner les objets
    }

    //FIXME implementation temporaire pour finir la partie
   public void end(){

    }
}
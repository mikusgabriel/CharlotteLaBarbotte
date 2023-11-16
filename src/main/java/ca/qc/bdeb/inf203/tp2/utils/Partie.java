package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Partie {
    private final Charlotte charlotte;

    // Constructeur : on crée les objets de la partie
    public Partie(Canvas canvas) {
        this.charlotte = new Charlotte(canvas);
    }

    public void update(double deltaTemps) {
        charlotte.update(deltaTemps);
        charlotte.isDead();

// Tester les collisions
// Autres : vérifie si on a gagné/perdu, ...
    }

    public void draw(GraphicsContext context) {
        charlotte.draw(context);
// Dessiner les objets
    }

    //FIXME implementation temporaire pour finir la partie
   public void end(){

    }
}
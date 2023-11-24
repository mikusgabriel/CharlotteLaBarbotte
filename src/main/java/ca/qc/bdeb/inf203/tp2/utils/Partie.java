package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    public void draw(GraphicsContext context, Camera camera) {
        charlotte.draw(context, camera);
    }

    //FIXME implementation temporaire pour finir la partie
   public void end(){

    }

    // --------GETTERS--------
    public Charlotte getCharlotte() {
        return charlotte;
    }
}
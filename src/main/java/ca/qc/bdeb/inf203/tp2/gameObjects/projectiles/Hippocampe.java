package ca.qc.bdeb.inf203.tp2.gameObjects.projectiles;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Hippocampe extends Projectile {

    public Hippocampe(double x, double y) {
        image = new Image("hippocampe.png");
        this.x = x;
        this.y = y;
        vx = 800;
        vy = 0;
        hauteur = image.getHeight();
        largeur = image.getWidth();
        ax = 0;
    }

    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics, camera);
    }

    @Override
    protected void updatePhysique(double deltaTemps) {
        super.updatePhysique(deltaTemps);

    }
}

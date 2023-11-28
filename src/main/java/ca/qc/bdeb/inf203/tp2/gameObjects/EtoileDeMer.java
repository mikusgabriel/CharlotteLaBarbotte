package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EtoileDeMer extends Projectile{

    private final int LARGEUR=36, HAUTEUR=35;

    public EtoileDeMer(double x,double y){
        image=new Image("etoile.png");
        this.x=x;
        this.y=y;
        vx=800;
    }
    @Override
    public boolean isDead() {
        return false;
    }
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics,camera);
    }

    @Override
    public void move() {

    }

    @Override
    public void updateIconeProjectile() {

    }
}

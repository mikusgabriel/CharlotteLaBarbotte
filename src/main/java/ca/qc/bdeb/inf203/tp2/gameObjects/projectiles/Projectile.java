package ca.qc.bdeb.inf203.tp2.gameObjects.projectiles;

import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import ca.qc.bdeb.inf203.tp2.gameObjects.GameObject;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Projectile extends GameObject {
    @Override
    protected void updatePhysique(double deltaTemps) {
        super.updatePhysique(deltaTemps);
    }

    @Override
    public void update(double deltaTemps, Camera camera) {
        super.update(deltaTemps, camera);
    }

    public void update(double deltaTemps, Camera camera, ArrayList<Ennemi> ennemis) {
        super.update(deltaTemps, camera);
    }

    //--------GETTERS--------
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

}

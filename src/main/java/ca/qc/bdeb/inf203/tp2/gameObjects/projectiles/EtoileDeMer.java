package ca.qc.bdeb.inf203.tp2.gameObjects.projectiles;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EtoileDeMer extends Projectile{


    private final int LARGEUR=36, HAUTEUR=35;

    public EtoileDeMer(double x,double y){
        image=new Image("etoile.png");
        this.x=x;
        this.y=y;
        vx = 800;
        vy = 0;
        hauteur = HAUTEUR;
        largeur= LARGEUR;
        ax = 0;
    }



}

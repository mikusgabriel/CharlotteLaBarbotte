package ca.qc.bdeb.inf203.tp2.gameObjects.projectiles;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Hippocampe extends Projectile {

    private final double HAUTEUR=36 ,LARGEUR=20, Y_INITIALE;

    public Hippocampe(double x, double y){
        image=new Image("hippocampe.png");
        this.x=x;
        this.y=y;
        this.Y_INITIALE=y;
        this.hauteur=HAUTEUR;
        this.largeur=LARGEUR;
        vy=0;
        vx=500;
        ax=0;
        ay=0;


    }
    @Override
    public void update(double deltaTemps,Camera camera){
        super.update(deltaTemps,camera);
        Random ran=new Random();
        double amplitude=(ran.nextInt(30,61)* (ran.nextBoolean() ? 1:-1));
        y=amplitude*Math.sin(2*Math.PI*deltaTemps/ran.nextInt(1,4))+Y_INITIALE;
    }


    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics,camera);
    }
}

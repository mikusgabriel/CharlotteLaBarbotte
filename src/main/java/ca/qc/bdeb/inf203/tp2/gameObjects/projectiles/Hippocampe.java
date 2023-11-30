package ca.qc.bdeb.inf203.tp2.gameObjects.projectiles;

import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Hippocampe extends Projectile {

    private final double HAUTEUR=36 ,LARGEUR=20, yInitiale = 260;
    double timeSinceAlive;
    double amplitude;
    int periode;
    public Hippocampe(double x, double y){
        image=new Image("hippocampe.png");
        this.x = x;
        this.y = y;
        this.hauteur=HAUTEUR;
        this.largeur=LARGEUR;
        vy = 0;
        vx = 500;
        ax = 0;
        ay = 0;

        amplitude = (new Random().nextInt(30,61) * (new Random().nextBoolean() ? 1:-1));
        periode = (new Random().nextInt(1,4));
    }
    @Override
    public void update(double deltaTemps, ArrayList<Ennemi> ennemis, Camera camera){
        super.update(deltaTemps,camera);
        timeSinceAlive += deltaTemps;

        // Formule pour translation vertical : y = amplitude * sin(2PIt/période) + yInitial
        y = amplitude * Math.sin(2*Math.PI*timeSinceAlive/periode) + yInitiale;
    }


    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics,camera);
    }
}

package ca.qc.bdeb.inf203.tp2.gameObjects;


import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;


public class Ennemi extends GameObject{
    private static final int LARGEUR_OG=120, HAUTEUR_OG=104;
    private boolean hasAttacked = false;

    private static Random r=new Random();

    private final Image[] imagesEnnemi= {new Image("poisson1.png"),new Image("poisson2.png"),new Image("poisson3.png"),new Image("poisson4.png"),new Image("poisson5.png")};

    public Ennemi(Canvas canvas,int niveau) {
        image= choisirImage();
        hauteur=r.nextInt(50,121);
        largeur=(int)(((double) LARGEUR_OG /HAUTEUR_OG)*hauteur);
        //A CHANGER IMPORTANT IMPORTANT IMPORTANT
        x=950;
        y=r.nextDouble(0.2*520,0.8*520);
        ax=-500;
        vx= -100* Math.pow(niveau,0.33)+200;
        //choisit une vitesse random entre 0 et 100 et soit - ou +
        vy= r.nextDouble(0,100)* (r.nextBoolean() ? 1:-1);
    }

    private Image choisirImage(){
        var r=new Random();
        return imagesEnnemi[r.nextInt(0,imagesEnnemi.length)];
    }

    @Override
    public boolean isDead() {
        return false;
    }
    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics, camera);
    }
    public void attack(Charlotte charlotte) {
        if(!hasAttacked)
            charlotte.perdreVie();
        hasAttacked = true;
    }

    //--------GETTERS--------

    @Override
    public double getX() {
        return super.getX();
    }
    @Override
    public double getY() {
        return super.getY();
    }
}

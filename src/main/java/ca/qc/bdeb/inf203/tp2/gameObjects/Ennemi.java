package ca.qc.bdeb.inf203.tp2.gameObjects;


import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;


public class Ennemi extends GameObject{
    private static final int LARGEUR_OG=120, HAUTEUR_OG=104;
    private boolean isAbleToAttack = true;

    public Ennemi(int niveau) {
        var r = new Random();
        image = new Image("poisson" + r.nextInt(1,6) + ".png");
        hauteur = r.nextInt(50,121);
        largeur=(int)(((double) LARGEUR_OG /HAUTEUR_OG)*hauteur);

        x=950;
        y=r.nextDouble(0.2*520,0.8*520);
        ax=-500;
        vx= -100* Math.pow(niveau,0.33)+200;
        //choisit une vitesse random entre 0 et 100 et soit - ou +
        vy= r.nextDouble(0,100)* (r.nextBoolean() ? 1:-1);
    }

    @Override
    public boolean isDead() {
        return false;
    }
    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics, camera);
    }

    /**
     * Un ennemi peut attacker Charlotte 1 seule fois.
     * Lorsque la methode est appelé, hasAttaqued est mis à true,
     * et l'ennemi ne peut plus attaquer Charlotte.
     */


    //--------GETTERS--------

    @Override
    public double getX() {
        return super.getX();
    }
    @Override
    public double getY() {
        return super.getY();
    }
    public boolean isAbleToAttack() {
        return isAbleToAttack;
    }

    //--------SETTERS--------
    public void setIsAbleToAttack(boolean isAbleToAttack){
        this.isAbleToAttack = isAbleToAttack;
    }
}

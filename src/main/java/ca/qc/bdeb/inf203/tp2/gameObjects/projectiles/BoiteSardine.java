package ca.qc.bdeb.inf203.tp2.gameObjects.projectiles;

import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import ca.qc.bdeb.inf203.tp2.gui.Fenetre;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class BoiteSardine extends Projectile {

    private static final int LARGEUR=36,
            HAUTEUR=35,
            Q_SARDINES=200,
            VITESSEMAX_X=500,
            VITESSEMIN_X=300,
            VITESSEMAX_Y=500,
            VITESSEMIN_Y=-500;

    private double forceCoulomb=0;

    public BoiteSardine(double x, double y){
        image=new Image("sardines.png");
        this.x=x;
        this.y=y;
        this.hauteur=HAUTEUR;
        this.largeur=LARGEUR;
        vx=300;
        vy=0;
    }


    @Override
    public void update(double deltaTemps, ArrayList<Ennemi> ennemis, Camera camera){
        super.update(deltaTemps, camera);
        int qEnnemi=-100;
        int K = 1000;

        double forceEnX = 0;
        double forceEnY = 0;


        for(Ennemi ennemi:ennemis){
            if(ennemi.getX() >  x) {
                double distance = getDistance(ennemi);
                double deltaX = x - ennemi.getX();
                double deltaY = y - ennemi.getY();

                // On normalise : ça revient au vecteur unitaire
                // dans la direction de la force


                double proportionX = deltaX / distance;
                double proportionY = deltaY / distance;
                forceCoulomb = (K * qEnnemi * Q_SARDINES) / (distance * distance);

                // On calcule la proportion de la force en X vs en Y
                forceEnX += forceCoulomb * proportionX;
                forceEnY += forceCoulomb * proportionY;
            }

        }

        //Avec nos simplifications, la force ici est égale
        // à l'accélération causée par le poisson sur la boîte
        ax=forceEnX;
        ay=forceEnY;

        // Rebondir
        if(y < 0 || y > Fenetre.HAUTEUR - image.getHeight())
            vy = -vy;

        if(vx>VITESSEMAX_X){
            vx=VITESSEMAX_X;
        }
        if(vx<VITESSEMIN_X){
            vx=VITESSEMIN_X;
        }
        if(vy<VITESSEMIN_Y){
            vy=VITESSEMIN_Y;
        }
        if(vy>VITESSEMAX_Y){
            vy=VITESSEMAX_Y;
        }
    }
    public double getDistance(Ennemi ennemi){
        double distance = Math.sqrt(Math.pow((ennemi.getX() - x) , 2) + Math.pow((ennemi.getY() - y), 2));
        return Math.max(distance, 0.01);
    }




}

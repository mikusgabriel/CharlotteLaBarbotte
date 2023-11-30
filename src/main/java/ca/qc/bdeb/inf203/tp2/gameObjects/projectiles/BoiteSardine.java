package ca.qc.bdeb.inf203.tp2.gameObjects.projectiles;

import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class BoiteSardine extends Projectile {

    private final int LARGEUR=36,
            HAUTEUR=35,
            Q_SARDINES=-200,

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
//doivent rebondir

    public void update(double deltaTemps, ArrayList<Ennemi> ennemis, Camera camera){
        //je ne sais pas si je dois le mettre en attribut pense pas
        int qEnnemi=-100;
        int K=1000;

        double forceEnX = 0;
        double forceEnY = 0;

        // TODO: Vérifier si la boîte de sardine est bel et bien
// à GAUCHE du poisson (on ne considère pas les poissons
        for(Ennemi ennemi:ennemis){
            double distance = getDistance(ennemi);
            double deltaX = x - ennemi.getX();
            double deltaY = y - ennemi.getY();

            // On normalise : ça revient à la vecteur unitaire
            // dans la direction de la force


            double proportionX = deltaX / distance;
            double proportionY = deltaY / distance;
            forceCoulomb+=(K*qEnnemi*Q_SARDINES)/distance;

            // On calcule la proportion de la force en X vs en Y
            forceEnX += forceCoulomb * proportionX;
            forceEnY += forceCoulomb * proportionY;

        }


        //Avec nos simplifications, la force ici est égale
        // à l'accélération causée par le poisson sur la boîte
        ax=forceEnX;
        ay=forceEnY;

        if(vx>VITESSEMAX_X){
            vx=VITESSEMAX_X;
        }else if(vx<VITESSEMIN_X){
            vx=VITESSEMIN_X;
        }
        if(vy<VITESSEMIN_Y){
            vy=VITESSEMIN_Y;
        }else if(vy>VITESSEMAX_Y){
            vy=VITESSEMAX_Y;
        }
        super.update(deltaTemps,camera,ennemis);
    }
    public double getDistance(Ennemi ennemi){
        double distance= Math.sqrt(Math.pow((ennemi.getX() - x),2) + Math.pow((ennemi.getY() - y),2));
        return Math.max(distance, 0.01);
    }




}

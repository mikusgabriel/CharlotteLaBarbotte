package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class BoiteSardine extends Projectile{

    private final int LARGEUR=36, HAUTEUR=35, Q_SARDINES=-200;

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
    @Override
    public void update(double deltaTemps, Camera camera){
        super.update(deltaTemps,camera);



    }

    public void updateMouvement(double deltaTemps, Ennemi ennemi){

        //je ne sais pas si je dois le mettre en attribut pense pas
        int qEnnemi=-100;
        int K=1000;
        //choisir le
        double ForceCoulomb=(K*qEnnemi*Q_SARDINES)/getDistance(ennemi);

    }
    public double getDistance(Ennemi ennemi){
        return Math.sqrt(Math.pow((ennemi.getX() - x),2) + Math.pow((ennemi.getY() - y),2));
    }




}

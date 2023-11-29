package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.image.Image;

public class BoiteSardine extends Projectile{

    public BoiteSardine(double x, double y){
        image=new Image("sardines.png");
        this.x=x;
        this.y=y;
    }


    @Override
    public void move() {

    }


}

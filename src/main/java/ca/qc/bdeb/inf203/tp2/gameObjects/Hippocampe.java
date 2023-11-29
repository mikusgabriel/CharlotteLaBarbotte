package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.image.Image;

public class Hippocampe extends Projectile{

    public Hippocampe(double x, double y){
        image=new Image("hippocampe.png");
        this.x=x;
        this.y=y;
    }

    @Override
    public void move() {

    }
}

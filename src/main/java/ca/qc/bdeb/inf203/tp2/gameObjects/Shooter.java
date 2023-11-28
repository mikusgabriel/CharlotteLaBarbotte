package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class Shooter{
    Projectile projectile;
    BoiteSardine boiteSardine;
    Hippocampe hippocampe;
    EtoileDeMer etoileDeMer;

    private double x, y;
    public Shooter(double x,double y) {
        this.x=x;
        this.y=y;
        projectile=new EtoileDeMer(x,y);

    }

    public Projectile tirer(){
        return projectile;
    }






    //setters de projectiles
    public void setBoiteSardine(){
        projectile=new BoiteSardine(x,y);
    }
    public void setEtoileDeMer() {
        projectile = new EtoileDeMer(x, y);
    }
    public void setHippocampe(){
        projectile=new Hippocampe(x,y);
    }


}

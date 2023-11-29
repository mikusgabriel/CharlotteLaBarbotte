package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class Shooter{
    private boolean isShooting;

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
    public void suivreCharlotte(double x, double y){
        this.x=x;
        this.y=y- projectile.getHauteur()/2;
    }

    public Projectile tirer(){
        return projectile;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
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

    //getters

    public Projectile getProjectile(){
        return projectile;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

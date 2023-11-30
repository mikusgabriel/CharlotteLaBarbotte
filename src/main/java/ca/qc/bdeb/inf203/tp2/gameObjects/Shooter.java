package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.BoiteSardine;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.EtoileDeMer;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Hippocampe;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Projectile;

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
        projectile = new EtoileDeMer(x,y);
    }
    public void suivreCharlotte(double x, double y){
        this.x=x;
        this.y=y - projectile.getHauteur()/2;
    }

    public Projectile tirer(){
        if (projectile.getClass().equals(Hippocampe.class)) {
            return new Hippocampe(x, y);
        } else if (projectile.getClass().equals(BoiteSardine.class)) {
            return new BoiteSardine(x, y);
        } else {
            return new EtoileDeMer(x, y);
        }
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
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

    public void setProjectile(Projectile projectileChoisit) {
        this.projectile = projectileChoisit;
    }
}

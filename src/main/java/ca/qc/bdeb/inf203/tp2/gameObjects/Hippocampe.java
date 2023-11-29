package ca.qc.bdeb.inf203.tp2.gameObjects;

public class Hippocampe extends Projectile{

    public Hippocampe(double x, double y){
        this.x=x;
        this.y=y;
    }

    @Override
    public void move() {

    }

    @Override
    boolean isDead() {
        return false;
    }

    @Override
    public void updateIconeProjectile() {

    }
}

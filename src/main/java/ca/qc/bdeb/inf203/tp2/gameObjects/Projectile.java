package ca.qc.bdeb.inf203.tp2.gameObjects;

public abstract class Projectile extends GameObject{




    public abstract void move();

    public abstract void updateIconeProjectile();





    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

}

package ca.qc.bdeb.inf203.tp2.gameObjects;

public class Ennemi extends GameObject{
    //a changer (le constructeur)
    protected Ennemi(double x, double y, double hauteur, double largeur) {
        super(x, y, hauteur, largeur);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public boolean isDead() {
        return false;
    }
}

package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
    protected double x, y, hauteur, largeur, vx = 0, vy = 0;
    protected Image image;

    private GameObject(){
        x = 0;
        y = 0;
        hauteur = 0;
        largeur = 0;
    }

    protected GameObject(double x, double y, double hauteur, double largeur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public abstract void update(double deltaTime);

    public abstract boolean isDead();

    public void draw(GraphicsContext graphics) {
        if (image != null) {
            graphics.drawImage(getImage(), 0, 0, getImage().getWidth(), getImage().getHeight(), getX(), getY(), getLargeur(), getHauteur());
        }
    }

    //--------GETTERS--------
    protected double getX() {
        return x;
    }

    protected double getY() {
        return y;
    }

    protected double getHauteur() {
        return hauteur;
    }

    protected double getLargeur() {
        return largeur;
    }

    protected Image getImage() {
        return image;
    }
}

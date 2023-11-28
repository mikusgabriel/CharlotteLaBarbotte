package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
    protected double x, y, hauteur, largeur, vx, vy, ax, ay;
    protected Image image;

    //maybe useless?
    protected GameObject(){
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

    public void update(double deltaTemps, Camera camera){
        updatePhysique(deltaTemps);
    }


    public boolean isTouching(GameObject otherObject) {
        double horizontalDistance = Math.abs(otherObject.getX() - this.x),
                verticalDistance = Math.abs(otherObject.getY() - this.y);

        return horizontalDistance <= this.hauteur && verticalDistance <= this.largeur;
    }



    /**
     * Change la vitesse et acceleration de l'objet enfant
     */
    protected void updatePhysique(double deltaTemps) {
        vx += deltaTemps * ax;
        vy += deltaTemps * ay;

        x += deltaTemps * vx;
        y += deltaTemps * vy;
    }

    public void draw(GraphicsContext graphics, Camera camera) {
        if (image != null) {
            graphics.drawImage(getImage(), 0, 0, getImage().getWidth(), getImage().getHeight(), getX()- camera.getX(), getY(), getLargeur(), getHauteur());
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

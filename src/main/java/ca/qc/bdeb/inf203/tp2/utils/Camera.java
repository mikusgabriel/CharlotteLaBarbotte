package ca.qc.bdeb.inf203.tp2.utils;

/**
 * La caméra nous permet de translater
 * ce qui se passe dans le monde
 * à devant l'écran
 */
public class Camera {
    private final double largeur;
    private double x;

    public Camera(double x, double width) {
        this.x = x;
        this.largeur = width;
    }

    //--------GETTERS--------
    public double getX() {
        return x;
    }
    public double getLargeur() {
        return largeur;
    }

    // --------SETTERS--------
    public void setX(double x) {
        this.x = x;
    }
}
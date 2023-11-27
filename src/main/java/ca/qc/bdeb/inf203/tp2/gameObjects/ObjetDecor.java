package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class ObjetDecor extends GameObject {
    private final double x;
    private final double y;
    private final Image imgDecor;

    /**
     * Constructeur d'un objet decor
     * @param x position dans le monde ou le decor doit apparaitre
     */
    public ObjetDecor(double x, double y) {
        var rand = new Random();
        var randImgNumber = rand.nextInt(1, 7);

        //Choisi une image random du fichier "resources"
        this.imgDecor = new Image("./decor" + randImgNumber + ".png");
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    /**
     * Verifie si le GameObject est visible par le joueur (dans l'écran)
     * @return True or False
     */
    public boolean isInView(Camera camera) {
        return (camera.getX() - imgDecor.getWidth() < x);
    }

    /**
     * Dessine un objet decor
     * @param context le context ou on dessine l'image
     * @param camera on deplace l'image selon la camera pour qu'il reste immobile dans le monde
     */
    public void draw(GraphicsContext context, Camera camera) {
        var xEcran = x - camera.getX();
        context.drawImage(imgDecor, xEcran, y);
    }

    //--------GETTERS--------

    public Image getImgDecor() {
        return imgDecor;
    }
}

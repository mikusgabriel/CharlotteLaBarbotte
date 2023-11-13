package ca.qc.bdeb.inf203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
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



    private double hauteur;
    private double largeur;

    private double x;
    private double y;

    protected Image image;
    private double vx = 0;
    private double vy = 0;

    public abstract void update(double deltaTime);

    public abstract boolean isDead();

    public void draw(GraphicsContext graphics) {
        System.out.println("bbb");
        System.out.println("Image: " + image); // Check the image
        System.out.println("Position: (" + x + ", " + y + ")"); // Check the position
        if (image != null) {
            System.out.println("aaaa");
            graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), x, y, largeur, hauteur);
        }
    }

}

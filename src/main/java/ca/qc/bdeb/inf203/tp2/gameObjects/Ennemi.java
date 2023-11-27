package ca.qc.bdeb.inf203.tp2.gameObjects;


import ca.qc.bdeb.inf203.tp2.utils.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;


public class Ennemi extends GameObject{
    private static final int LARGEUR_OG = 120, HAUTEUR_OG = 104;
    private boolean isAbleToAttack = true;
    private final Image[] images = {
            new Image("poisson1.png"),
            new Image("poisson2.png"),
            new Image("poisson3.png"),
            new Image("poisson4.png"),
            new Image("poisson5.png")};

    /**
     * Constructeur d'un poisson ennemi
     * @param niveau L'ennemi se comporte différemment selon le niveau
     */
    public Ennemi(int niveau) {
        var rand = new Random();

        image = choisirImage();
        hauteur = rand.nextInt(50,121);
        largeur = (int)(LARGEUR_OG / HAUTEUR_OG * hauteur); // Échelle 1:1 selon hauteur
        x = 950;
        ax = -500;

        // Formule pour vx : -100 * niveau^0.33 + 200
        vx = -100 * Math.pow(niveau, 0.33) + 200;
        y = rand.nextDouble(0.2 * 520,0.8 * 520);

        //choisit une vy random entre 0 et 100 et soit - ou +
        vy= rand.nextDouble(0,100) * (rand.nextBoolean() ? 1:-1);
    }

    private Image choisirImage(){
        return images[(new Random()).nextInt(0, images.length)];
    }
    @Override
    public boolean isDead() {
        return false;
    }
    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics, camera);
    }

    //--------GETTERS--------

    @Override
    public double getX() {
        return super.getX();
    }
    @Override
    public double getY() {
        return super.getY();
    }
    public boolean isAbleToAttack() {
        return isAbleToAttack;
    }

    //--------SETTERS--------
    public void setIsAbleToAttack(boolean isAbleToAttack){
        this.isAbleToAttack = isAbleToAttack;
    }
}

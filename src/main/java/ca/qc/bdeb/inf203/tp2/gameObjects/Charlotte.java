package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import ca.qc.bdeb.inf203.tp2.utils.Input;
import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Charlotte extends GameObject {
    private final static int PV_MAX = 4, V_MAX = 300;
    private int vie;
    private double x, y;
    private boolean moved = false;

    /**
     * Constructeur de Charlotte
     */
    public Charlotte() {
        this.x = 0;
        this.y = 260;
        this.image = new Image("charlotte.png");
        this.vie = PV_MAX;
    }
    @Override
    public void update(double deltaTemps, Camera camera){
        //call update du super qui call update physique dans GameObject
        super.update(deltaTemps,camera);

        boolean gauche = Input.isKeyPressed(KeyCode.LEFT);
        boolean droite = Input.isKeyPressed(KeyCode.RIGHT);
        boolean haut = Input.isKeyPressed(KeyCode.UP);
        boolean bas = Input.isKeyPressed(KeyCode.DOWN);

        if(isDead()) {
            ax = 0;
            ay = 0;
            vx = 0;
            vy = 0;
            image = new Image("charlotte-outch.png");
            return;
        }

        // Translation
        // Horizontale
        if(gauche) {
            ax = -1000;
        }
        else if(droite) {
            image = new Image("charlotte-avant.png");
            ax = 1000;
            avancerCamera(camera, deltaTemps);
        }
        else {
            ax = 0;
            vx = ralentir(deltaTemps,vx);
            image = new Image("charlotte.png");
        }
        // Verticale
        if(haut) {
            ay = -1000;
        }
        else if(bas) {
            ay = 1000;
        }
        else {
            ay = 0;
            vy = ralentir(deltaTemps,vy);
        }

        // Collisions avec rebords
        // Bas du monde
        if(image.getHeight() + y >= Partie.HAUTEUR_MONDE) {
            y = Partie.HAUTEUR_MONDE - image.getHeight() - 1;
            vy = 0;
        }
        // Top du monde
        if(y <= 0) {
            y = 1;
            vy = 0;
        }
        // Arrière
        if(x <= camera.getX()) {
            x = camera.getX() + 1;
            vx = 0;
        }
        // Fin du monde
        if(x >= Partie.LONGUEUR_MONDE) {
            x = Partie.LONGUEUR_MONDE - 1;
            vx = 0;
        }

        vx = vitesseMax(vx);
        vy = vitesseMax(vy);
        moved = x > 1;
    }

    // Méthode du prof pour faire diminuer la vitesse d'un object
    private double ralentir(double deltaTemps,double v){
    // Quand on relâche : on RALENTI au lieu de stopper
        int signeVitesse = v > 0 ? 1 : -1;
        double vitesseAmortissementX = -signeVitesse * 500;
        v += deltaTemps * vitesseAmortissementX;
        int nouveauSigneVitesse = v > 0 ? 1 : -1;
    // On évite un petit glitch :
    // si le signe change, la vitesse tombe à zéro
        if(nouveauSigneVitesse != signeVitesse) {
            v = 0;
        }
        return v;
    }

    // La vitesse ne peut pas dépasser 300
    private double vitesseMax(double v){
        if(v > V_MAX)
            v = V_MAX;
        else if(v < -V_MAX)
            v = -V_MAX;

        return v;
    }

    public void perdreVie() {
        image = new Image("charlotte-outch.png");
        if (vie > 0)
            vie--;
        else
            vie = 0;
    }

    @Override
    public boolean isDead() {
        return vie == 0;
    }

    /**
     * Fait avancer la caméra lorsque Charlotte atteint le 1/5 de celui.
     * @param camera La classe Camera permet de voir Charlotte sur l'écran à tout moment
     */
    private void avancerCamera(Camera camera, double deltaTemps) {
        if(x - camera.getX() >= camera.getWidth() / 5
                // Caméra avance jusqu'au moment où on atteint la fin du monde
                && camera.getX() + camera.getWidth() <= Partie.LONGUEUR_MONDE) {
            camera.setX(camera.getX() + vx * deltaTemps);
        }
    }

    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics,camera);
    }

    //--------GETTERS--------
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getVie() {
        return vie; }
    public boolean isMoved() {
        return moved;
    }
}

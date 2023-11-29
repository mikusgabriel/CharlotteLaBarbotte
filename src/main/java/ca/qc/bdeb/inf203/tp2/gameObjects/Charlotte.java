package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.utils.Camera;
import ca.qc.bdeb.inf203.tp2.utils.Input;
import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Charlotte extends GameObject {
    private final static int PV_MAX = 4, V_MAX=300;
    private int vie = PV_MAX;
    private final static double LARGEUR = 102, HAUTEUR =90, SHOOTER_COOLDOWN = 100;
    private boolean moved = false;
    private boolean projectileFired = false;
    private double timeSinceLastShot = 100;

    private final Shooter shooter;

    /**
     * Constructeur de Charlotte
     */
    public Charlotte() {
        super(0, 260, HAUTEUR, LARGEUR);
        image = new Image("charlotte.png");
        shooter = new Shooter(getX()+ LARGEUR / 2,getY()+ HAUTEUR / 2);
    }
    @Override
    public void update(double deltaTemps, Camera camera){
        //call update du super qui call update physique dans Gameobject
        super.update(deltaTemps,camera);
        shooter.suivreCharlotte(getX()+ LARGEUR / 2,getY()+ HAUTEUR / 2);
        boolean gauche = Input.isKeyPressed(KeyCode.LEFT);
        boolean droite = Input.isKeyPressed(KeyCode.RIGHT);
        boolean haut = Input.isKeyPressed(KeyCode.UP);
        boolean bas = Input.isKeyPressed(KeyCode.DOWN);
        boolean tirer = Input.isKeyPressed(KeyCode.SPACE);
        avancerCamera(camera, deltaTemps);


        if(isDead()) {
            ax = 0;
            ay = 0;
            vx = 0;
            vy = 0;
            image = new Image("charlotte-outch.png");
            return;
        }


        // Tirer un projectile
        timeSinceLastShot++;
        if(tirer && timeSinceLastShot >= SHOOTER_COOLDOWN) {
            shooter.setEtoileDeMer();
            shooter.setShooting(true);
            timeSinceLastShot = 0;
            System.out.println("shoot");
        }
        else shooter.setShooting(false);

        // Translation
        // Horizontale
        if(gauche) {
            ax = -1000;
        }
        else if(droite) {
            image = new Image("charlotte-avant.png");
            ax = 1000;

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
        // Bas
        if(image.getHeight() + y >= Partie.HAUTEUR_MONDE) {
            y = Partie.HAUTEUR_MONDE - image.getHeight() - 1;

            vy = 0;
        }
        // Haut
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


    public boolean isDead() {
        return vie == 0;
    }

    private void avancerCamera(Camera camera, double deltaTemps) {
        if(x - camera.getX() >= camera.getWidth() / 5
                // Caméra avance seulement si la fin du monde n'a pas ete atteinte
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
    public int getVie() { return vie;}
    public boolean isMoved() {
        return moved;
    }
    public Shooter getShooter(){ return shooter;}
}

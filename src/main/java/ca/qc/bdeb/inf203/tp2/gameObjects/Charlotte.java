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
    private final static double LARGEUR = 102, HAUTEUR =90, SHOOTER_COOLDOWN = 0.5;
    private boolean invulnerable, showImageBool = true;
    private double timeSinceLastShot = 100, counter = 0;
    private static final int INVULNERABILITY_TIMER = 125, FLASH_FRAMES = 15;
    private final Shooter shooter;

    /**
     * Constructeur de Charlotte
     */
    public Charlotte() {
        super(0, 260, HAUTEUR, LARGEUR);
        image = new Image("charlotte.png");
        shooter = new Shooter(getX()+ LARGEUR / 2,getY()+ HAUTEUR / 2);
        invulnerable = false;
    }

    /**
     * Mis à jour de tous les paramètres et attributs de Charlotte à chaque fois qu'on met à jour la partie.
     * @param deltaTemps interval de temps pour update en nanoseconde
     * @param camera la Camera
     */
    @Override
    public void update(double deltaTemps, Camera camera){
        //call update du super qui call update physique dans GameObject
        super.update(deltaTemps,camera);
        shooter.suivreCharlotte(getX()+ LARGEUR / 2,getY()+ HAUTEUR / 2);
        boolean gauche = Input.isKeyPressed(KeyCode.LEFT);
        boolean droite = Input.isKeyPressed(KeyCode.RIGHT);
        boolean haut = Input.isKeyPressed(KeyCode.UP);
        boolean bas = Input.isKeyPressed(KeyCode.DOWN);
        boolean tirer = Input.isKeyPressed(KeyCode.SPACE);

        // Avancer la camera avec Charlotte
        avancerCamera(camera, deltaTemps);

        // Condition pour perdre des vies
        if(invulnerable)
            isHit();

        // Condition pour mourir
        if(isDead()) {
            ax = 0;
            ay = 0;
            vx = 0;
            vy = 0;
            image = new Image("charlotte-outch.png");
            return;
        }

        // Tirer un projectile
        timeSinceLastShot += deltaTemps;
        if(tirer && timeSinceLastShot >= SHOOTER_COOLDOWN) {

            shooter.setShooting(true);
            timeSinceLastShot = 0;
        }
        else shooter.setShooting(false);

        // Translation
        // Horizontale
        if(gauche) {
            ax = -1000;
        }
        else if(droite) {
            if(!invulnerable)
                image = new Image("charlotte-avant.png");
            ax = 1000;
        }
        else {
            ax = 0;
            vx = ralentir(deltaTemps,vx);
            if(!invulnerable)
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
        invulnerable = true;

        if (vie > 0)
            vie--;
        else
            vie = 0;
    }


    public boolean isDead() {
        return vie == 0;
    }

    private void avancerCamera(Camera camera, double deltaTemps) {
        if(x - camera.getX() >= camera.getLargeur() / 5
                // Caméra avance seulement si la fin du monde n'a pas ete atteinte
                && camera.getX() + camera.getLargeur() <= Partie.LONGUEUR_MONDE) {
            camera.setX(camera.getX() + vx * deltaTemps);
        }
    }

    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        if(invulnerable && vie > 0) {
            // Lorsque showImageBool est VRAI,
            // On dessine Charlotte
           if(showImageBool)
               super.draw(graphics, camera);
        }
        else super.draw(graphics,camera);
    }

    /**
     * Cette méthode est appelée lorsque Charlotte a été touché par un ennemi.
     * Elle fait "clignoter" Charlotte pendant quelques secondes pour montrer que charlotte
     * a perdu de la vie. Elle enlève aussi l'invincibilité.
     */
    private void isHit() {
        image = new Image("charlotte-outch.png");

        // À chaque 0.25 seconde, on alterne un booléen showImageBool
        if(counter % FLASH_FRAMES == 0) {
            showImageBool = !showImageBool;
        }

        // Lorsque le compteur est égal au temps d'invincibilité,
        // On réinitialise
        if(counter == INVULNERABILITY_TIMER) {
            invulnerable = false;
            counter = 0;
        }
        counter++;
    }

    //--------GETTERS--------
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getVie() { return vie;}
    public Shooter getShooter(){ return shooter;}
    public boolean isInvulnerable() {
        return invulnerable;
    }

    //--------SETTERS--------
    public void setX(int x) {
        this.x = x;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }
}

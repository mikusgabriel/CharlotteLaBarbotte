package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.*;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.EtoileDeMer;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Projectile;
import ca.qc.bdeb.inf203.tp2.gui.Fenetre;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;


public class Partie {
    private final Charlotte charlotte;
    private  BarreVie barreVie;
    private  final Canvas canvas;
    private  Camera camera;
    private final ArrayList<Ennemi> ennemis = new ArrayList<>();
    private final ArrayList<ObjetDecor> objetDecorList = new ArrayList<>();
    private final ArrayList<Projectile> projectileList = new ArrayList<>();
    private final ArrayList<Baril> barilList = new ArrayList<>();
    private Color backgroundColor;
    public final static int LONGUEUR_MONDE = 4160;
    public final static int HAUTEUR_MONDE = Fenetre.HAUTEUR;
    private int niveau;
    private double spawnTimer;
    private double currentTime;

    // Constructeur : on crée les objets de la partie
    public Partie() {
        this.canvas = new Canvas(Fenetre.LARGEUR, Fenetre.HAUTEUR);
        this.charlotte = new Charlotte();
        this.camera = new Camera(0, canvas.getWidth());
        this.backgroundColor = Color.hsb((new Random()).nextInt(190, 270), 0.84, 1.0);
        this.barreVie = new BarreVie(backgroundColor);
        niveau = 1;

        //generation decor au debut
        for (int filledArea = 0; filledArea < LONGUEUR_MONDE;)
            filledArea = genenerDecors(objetDecorList, filledArea);
    }

    public void newGame(int niveau) {
        objetDecorList.clear();
        charlotte.setX(0);
        this.camera = new Camera(0, canvas.getWidth());
        this.backgroundColor = Color.hsb((new Random()).nextInt(190, 270), 0.84, 1.0);
        this.niveau = niveau;

        //generation decor au debut
        for (int filledArea = 0; filledArea < LONGUEUR_MONDE;)
            filledArea = genenerDecors(objetDecorList, filledArea);
    }

    /**
     * À chaque intervalle de temps, on remet à jour tous les objets de la partie
     * @param deltaTemps interval de temps compté en nanoseconde
     */
    public void update(double deltaTemps)  {
        // Faire apparaitre les groupes d'ennemis
        if(!end())
            spawnEnnemiWave(niveau);

        // Update Charlotte
        charlotte.update(deltaTemps, camera);
        charlotte.isDead();

        // Update barre de vie
        barreVie.update(charlotte.getVie());

        // Update ennemis
        for (Ennemi ennemi : ennemis) {
            ennemi.update(deltaTemps,camera);

            // Teste Collision entre Charlotte et poisson
            // Si oui, alors ennemi attaque Charlotte
            if(charlotte.isTouching(ennemi) && !charlotte.isInvulnerable() && ennemi.isAbleToAttack()) {
                charlotte.perdreVie();
                ennemi.setIsAbleToAttack(false);
            }
        }

        // L'ennemi est enlevé de la liste lorsqu'il est derrière la caméra ou lorsqu'il est mort
        ennemis.removeIf(ennemi -> ennemi.getX() + ennemi.getLargeur() < camera.getX());
        ennemis.removeIf(Ennemi::isDead);

        // Update projectiles
        if(charlotte.getShooter().isShooting()) {
            projectileList.add(charlotte.getShooter().tirer());
            System.out.println(projectileList.size());
        }

        for(Projectile projectile : projectileList){
            projectile.update(deltaTemps, camera);
        }

        // Le projectile est enlevé de la liste lorsqu'il disparait de l'écran
        projectileList.removeIf(projectile -> projectile.getX() >= charlotte.getX() + Fenetre.LARGEUR);

        // Teste les collisions entre projectiles et poissons
        for(Ennemi ennemi : ennemis)
            for(Projectile projectile : projectileList)
                if(projectile.isTouching(ennemi))
                    ennemi.setDead(true);

        // Update condition fin de partie
        if(end()) {
            niveau ++;
            projectileList.clear();
            ennemis.clear();
            newGame(niveau);
        }
    }

    public void draw(GraphicsContext context) {
        // Dessiner le decor
        for (ObjetDecor objetDecor : objetDecorList) {
                objetDecor.draw(context, camera);
        }

        // Dessiner Charlotte
        charlotte.draw(context, camera);

        // Dessiner les ennemis
        for (Ennemi ennemi : ennemis) {
            ennemi.draw(context, camera);
        }

        // Dessiner les projectiles
        for(Projectile projectile : projectileList) {
            System.out.println(projectile.getY());
            projectile.draw(context, camera);
        }

        // Dessiner la barre de vie
        barreVie.draw(context,charlotte.getShooter().getProjectile());
    }

    /**
     * Methode qui genere les objets decors jusqu'à la fin du monde
     * @param objetDecorList ObjetDecor est placé dans un arrayList pour les appeler dans la methode draw()
     * @param filledArea Longueur totale de chaque objet et de la distance entre chaque
     * @return La nouvelle somme
     */
    public int genenerDecors(ArrayList<ObjetDecor> objetDecorList, int filledArea) {
        var distanceFromNext = (new Random()).nextInt(50, 100); // Distance entre decors est toujours contenue entre 50 et 100
        var decor = new ObjetDecor(distanceFromNext + filledArea, HAUTEUR_MONDE); // Decor est placé en bas du monde
        filledArea += distanceFromNext + (int) decor.getImgDecor().getWidth();

        objetDecorList.add(decor);

        return filledArea;
    }

    /** Test si le joueur a gagné
     * @return - Return un boolean selon si charlotte a atteint la fin du monde pour determiner si le joueur a complete le niveau
     */
    public boolean end() {
        return charlotte.getX() >= LONGUEUR_MONDE - 1;
    }

    private void spawnEnnemiWave(int niveau) {
        // Formule pour temps entre groupe d'ennemis : Nseconde = 0.75 + 1 / (niveau)^1/2
        spawnTimer = 0.75 + 1/Math.sqrt(niveau);
        currentTime ++;
        if(currentTime/500 >= spawnTimer) {
            for(int i = 0; i < (new Random()).nextInt(1,6); i++) {
                ennemis.add(new Ennemi(niveau, charlotte.getX() + Fenetre.LARGEUR));
                System.out.println("ennemi print");
            }
            currentTime = 0;
        }
    }


    //--------GETTERS--------
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public Canvas getCanvas() {
        return canvas;
    }
}
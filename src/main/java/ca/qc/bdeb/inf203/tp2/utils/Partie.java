package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.*;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Hippocampe;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Projectile;
import ca.qc.bdeb.inf203.tp2.gui.Fenetre;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe s'occupe de la logique du jeu
 * et ce qui doit être dessiné sur la fenêtre dans la page jeu
 */
public class Partie {
    public final static int LONGUEUR_MONDE = 4160, HAUTEUR_MONDE = Fenetre.HAUTEUR ;
    private final Charlotte charlotte;
    private final BarreVie barreVie;
    private final Canvas canvas;
    private final ArrayList<Ennemi> poissons;
    private final ArrayList<ObjetDecor> decors;
    private final ArrayList<Projectile> projectiles;
    private final Text affichageNiveau;
    private final Text nbDePoisson;
    private final Text nbDeProjectile;
    private final Text positionCharlotte;
    private Baril baril;
    private VBox menuDebug;
    private Camera camera;
    private Color backgroundColor;
    private int niveau;
    private double currentTime, textTimer, deathTimer;

    /**
     * Dans le constructeur, on crée les objects final qui ne change pas d'un niveau à l'autre : le canvas, Charlotte
     * et la barre de vie. On appelle newGame() pour créer le niveau 1.
     */
    public Partie() {
        this.canvas = new Canvas(Fenetre.LARGEUR, Fenetre.HAUTEUR);
        this.charlotte = new Charlotte();
        this.barreVie = new BarreVie(backgroundColor);
        this.poissons = new ArrayList<>();
        this.decors = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.affichageNiveau = new Text();
        this.menuDebug = new VBox();
        this.nbDePoisson = new Text();
        this.nbDeProjectile = new Text();
        this.positionCharlotte = new Text();

        newGame(1);

        initialiseMenuDebug();
    }

    private void initialiseMenuDebug() {
        menuDebug = new VBox();
        nbDePoisson.setFill(Color.WHITE);
        nbDeProjectile.setFill(Color.WHITE);
        positionCharlotte.setFill(Color.WHITE);
        menuDebug.getChildren().addAll(nbDePoisson, nbDeProjectile, positionCharlotte);
        menuDebug.setAlignment(Pos.TOP_LEFT);
        menuDebug.setPadding(new Insets(65, 0, 0, 30));
    }

    /**
     * Méthode appelée lorsqu'on veut générer un nouveau niveau.
     * Elle est appelée dans le constructeur pour créer le niveau 1 et après,
     * à chaque fois que Charlotte complète un niveau.
     * @param niveau le niveau actuel qui va changer le comportement des ennemis.
     */
    private void newGame(int niveau) {
        this.camera = new Camera(0, canvas.getWidth());
        this.backgroundColor = Color.hsb((new Random()).nextInt(190, 270), 0.84, 1.0);
        decors.clear();
        charlotte.setX(0);
        canvas.getGraphicsContext2D().setFill(backgroundColor);
        barreVie.setBackgroundColor(backgroundColor);
        baril = new Baril();
        this.niveau = niveau;
        showText();
        textTimer = 0;
        System.out.println("NEW LEVEL***********************************************************************************");

        //generation decor au debut
        for (int filledArea = 0; filledArea < LONGUEUR_MONDE;)
            filledArea = generateDecor(decors, filledArea);

    }

    /**
     * À chaque intervalle de temps, on remet à jour tous les objets de la partie
     * @param deltaTemps interval de temps compté en nanoseconde
     */
    public void update(double deltaTemps) {

        textTimer += deltaTemps;
        if (textTimer > 4)
            affichageNiveau.setY(-1);

        // Faire apparaitre les groupes d'ennemis
        if (!end() && !charlotte.isDead())
            spawnEnnemiWave(niveau, deltaTemps);

        // Update Charlotte
        charlotte.update(deltaTemps, camera);

        // Update Baril
        baril.update(deltaTemps, camera);
        if (baril.isTouching(charlotte)) {
            baril.isTouch(charlotte.getShooter());
        }

        // Update barre de vie
        barreVie.update(charlotte.getVie());

        // Update ennemis
        for (Ennemi ennemi : poissons) {
            ennemi.update(deltaTemps, camera);

            // Teste Collision entre Charlotte et poisson
            // Si oui, alors ennemi attaque Charlotte
            if (charlotte.isTouching(ennemi) && !charlotte.isInvulnerable() && ennemi.isAbleToAttack()) {
                charlotte.perdreVie();
                ennemi.setIsAbleToAttack(false);
            }
        }

        // L'ennemi est enlevé de la liste lorsqu'il est derrière la caméra ou lorsqu'il est mort
        poissons.removeIf(ennemi -> ennemi.getX() + ennemi.getLargeur() < camera.getX());
        poissons.removeIf(Ennemi::isDead);

        // Update projectiles
        if (charlotte.getShooter().isShooting()) {
            // Si c'est un hippocampe, ajouter 3 projectiles à la liste
            if (charlotte.getShooter().getProjectile().getClass() == Hippocampe.class) {
                projectiles.add(charlotte.getShooter().tirer());
                projectiles.add(charlotte.getShooter().tirer());
                projectiles.add(charlotte.getShooter().tirer());
            } else {
                projectiles.add(charlotte.getShooter().tirer());
            }
        }

        for (Projectile projectile : projectiles) {
            projectile.update(deltaTemps, poissons, camera);
        }

        // Le projectile est enlevé de la liste lorsqu'il disparait de l'écran
        projectiles.removeIf(projectile -> projectile.getX() >= charlotte.getX() + Fenetre.LARGEUR);

        // Teste les collisions entre projectiles et poissons
        for (Ennemi ennemi : poissons)
            for (Projectile projectile : projectiles)
                if (projectile.isTouching(ennemi))
                    ennemi.setDead(true);

        // Update condition victoire
        if (end()) {
            niveau++;
            projectiles.clear();
            poissons.clear();
            newGame(niveau);
        }
    }

    /**
     * Vue de l'utilisateur
     * @param context le root est un GraphicsContext
     */
    public void draw(GraphicsContext context) {

        if(textTimer > 4)
            affichageNiveau.setY(-1);
        if(end())
            textTimer = 0;

        nbDePoisson.setText("NB Poissons: " + poissons.size());
        nbDeProjectile.setText("NB Projectiles: " + projectiles.size());
        positionCharlotte.setText("Position Charlotte: " + charlotte.getX() / LONGUEUR_MONDE * 100 + "%");

        context.setFill(backgroundColor);
        context.fillRect(0 , 0, Fenetre.LARGEUR, Fenetre.HAUTEUR);

        // Dessiner le decor
        for (ObjetDecor objetDecor : decors) {
                objetDecor.draw(context, camera);
        }

        // Dessiner baril
        baril.draw(context, camera);

        // Dessiner Charlotte
        charlotte.draw(context, camera);

        // Dessiner les ennemis
        for (Ennemi ennemi : poissons) {
            ennemi.draw(context, camera);
        }

        // Dessiner les projectiles
        for(Projectile projectile : projectiles) {
            projectile.draw(context, camera);
        }

        // Dessiner la barre de vie
        barreVie.draw(context,charlotte.getShooter().getProjectile());

        if(charlotte.isDead()) {
            affichageNiveau.setY(300);
            affichageNiveau.setX(100);
            affichageNiveau.setText("FIN DE PARTIE");
            affichageNiveau.setFill(Color.DARKRED);
        }
    }

    /**
     * Methode qui genere les objets decors jusqu'à la fin du monde
     * @param objetDecorList ObjetDecor est placé dans un arrayList pour les appeler dans la methode draw()
     * @param filledArea Longueur totale de chaque objet et de la distance entre chaque
     * @return La nouvelle somme de filledArea
     */
    private int generateDecor(ArrayList<ObjetDecor> objetDecorList, int filledArea) {
        var distanceFromNext = (new Random()).nextInt(50, 100); // Distance entre decors est toujours contenue entre 50 et 100
        var decor = new ObjetDecor(distanceFromNext + filledArea, HAUTEUR_MONDE); // Decor est placé en bas du monde
        filledArea += distanceFromNext + (int) decor.getImgDecor().getWidth();

        objetDecorList.add(decor);

        return filledArea;
    }

    /** Test si le joueur a gagné
     * selon si Charlotte a atteint la fin du monde pour
     * determiner si le joueur a complété le niveau
     * @return Vrai ou faux
     */
    public boolean end() {
        return charlotte.getX() >= LONGUEUR_MONDE - 1;
    }

    /**
     * Fait apparaître un à cinq poissons ennemis à droite de l'écran à interval régulier.
     * @param niveau la difficulté des groupes de poisson augmente avec le niveau
     */
    private void spawnEnnemiWave(int niveau, double deltaTime) {
        // Formule pour temps entre groupe d'ennemis : Nseconde = 0.75 + 1 / (niveau)^1/2
        var spawnTimer = 0.75 + 1 / Math.sqrt(niveau);

        if(currentTime > spawnTimer) {
            for(int i = 0; i < (new Random()).nextInt(1,6); i++) {
                poissons.add(new Ennemi(niveau, charlotte.getX() + (double) (4 * Fenetre.LARGEUR) / 5 ));
                System.out.println("ennemi print");
            }
            currentTime = 0;
        }
        currentTime += deltaTime;
    }
    private void showText() {
        affichageNiveau.setText("NIVEAU " + niveau);
        affichageNiveau.setY(300);
        affichageNiveau.setX(300);
        affichageNiveau.setFill(Color.WHITE);
        affichageNiveau.setFont(Font.font(100));
    }

    //--------GETTERS--------
    public Canvas getCanvas() {
        return canvas;
    }
    public Text getAffichageNiveau() {return affichageNiveau;}

    public VBox getMenuDebug() {
        return menuDebug;
    }

    public Charlotte getCharlotte() {
        return charlotte;
    }
}
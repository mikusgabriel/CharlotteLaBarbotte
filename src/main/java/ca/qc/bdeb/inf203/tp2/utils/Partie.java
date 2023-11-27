package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.*;
import ca.qc.bdeb.inf203.tp2.gui.Fenetre;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;


public class Partie {
    private final Charlotte charlotte;
    private final BarreVie barreVie;
    private final Canvas canvas;
    private final Camera camera;
    private final ArrayList<Ennemi> ennemis = new ArrayList<>();
    private final ArrayList<ObjetDecor> objetDecorList = new ArrayList<>();
    private final ArrayList<Projectile> projectileList = new ArrayList<>();
    private final ArrayList<Baril> barilList = new ArrayList<>();
    private final Color backgroundColor;
    public final static int LONGUEUR_MONDE = 4160;
    public final static int HAUTEUR_MONDE = 900;

    // Constructeur : on crée les objets de la partie
    public Partie(int niveau) {
        this.canvas = new Canvas(Fenetre.LARGEUR_FENETRE, Fenetre.HAUTEUR_FENETRE);
        this.charlotte = new Charlotte();

        this.camera = new Camera(0, Fenetre.LARGEUR_FENETRE);


        this.backgroundColor = Color.hsb((new Random()).nextInt(190, 270), 0.84, 1.0);
        this.barreVie = new BarreVie(backgroundColor);

        for(int i = 0; i < 1; i++) {
            ennemis.add(new Ennemi(niveau));
            System.out.println("ennemi print");
        }

        //generation decor au debut
        for (int filledArea = 0; filledArea < LONGUEUR_MONDE;)
            filledArea = genenerDecors(objetDecorList, filledArea);
    }

    /**
     * À chaque interval de temps, on remet à jour tous les objets de la partie
     * @param deltaTemps interval de temps compté en nanoseconde
     */
    public void update(double deltaTemps)  {
        // Update Charlotte
        charlotte.update(deltaTemps, camera);
        charlotte.isDead();

        // Update barre de vie
        barreVie.update(charlotte.getVie());

        // Update ennemis
        for (Ennemi ennemi : ennemis) {
            if(charlotte.isMoved())
                ennemi.update(deltaTemps,camera);
            ennemi.isDead();

            // --Detection des collisions--
            // Teste si les coordonnées de Charlotte et de l'ennemi se touchent
            // Si oui, alors ennemi attaque Charlotte
            if(charlotte.isTouching(ennemi) && !ennemi.isDead() && ennemi.isAbleToAttack()) {
                charlotte.perdreVie();
                ennemi.setIsAbleToAttack(false);
            }
        }

        // Update condition fin de partie
        end();
    }

    public void draw(GraphicsContext context) {
        // Dessiner le decor
        for (ObjetDecor objetDecor : objetDecorList) {
            if (objetDecor.isInView(camera))
                objetDecor.draw(context, camera);
        }

        // Dessiner Charlotte
        charlotte.draw(context, camera);

        // Dessiner les ennemis
        for (Ennemi ennemi : ennemis) {
            ennemi.draw(context, camera);
        }

        // Dessiner barre de vie
        barreVie.draw(context);
    }

    /**
     * Methode qui genere les objets decors jusqu'à la fin du monde
     * @param objetDecorList ObjetDecor est placé dans un arrayList pour les appeler dans la methode draw()
     * @param filledArea Longueur totale de chaque objet et de la distance entre chaque
     * @return La nouvelle somme
     */
    public int genenerDecors(ArrayList<ObjetDecor> objetDecorList, int filledArea) {
        var distanceFromNext = (new Random()).nextInt(50, 100); // Distance entre decors est toujours contenue entre 50 et 100
        var decor = new ObjetDecor(distanceFromNext + filledArea, HAUTEUR_MONDE - 490); // Decor est placé en bas du monde
        filledArea += distanceFromNext + (int) decor.getImgDecor().getWidth();

        objetDecorList.add(decor);

        return filledArea;
    }

    /** Test si le joueur a gagné
     * @return - Return un boolean selon si charlotte a atteint la fin du monde pour determiner si le joueur a complete le niveau
     */
    public boolean end() {
        return charlotte.getX() >= LONGUEUR_MONDE;
    }

    //--------GETTERS--------
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public Canvas getCanvas() {
        return canvas;
    }
    public Charlotte getCharlotte() {
        return charlotte;
    }
}
package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.BarreVie;
import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import ca.qc.bdeb.inf203.tp2.gameObjects.ObjetDecor;
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
    private final Color backgroundColor;
    public final static int LONGUEUR_MONDE = 4160;


    // Constructeur : on crée les objets de la partie
    public Partie(int niveau) {
        this.canvas = new Canvas(Fenetre.LARGEUR_FENETRE, Fenetre.HAUTEUR_FENETRE);
        this.charlotte = new Charlotte(canvas);
        this.camera = new Camera(0,0, Fenetre.LARGEUR_FENETRE, Fenetre.HAUTEUR_FENETRE);
        this.backgroundColor = generateRandomColor();
        this.barreVie = new BarreVie(backgroundColor);

        for(int i=0;i<1;i++){
            ennemis.add(new Ennemi(canvas,niveau));
            System.out.println("ennemi print");
        }

        //generation decor au debut
        for (int filledArea = 0; filledArea < LONGUEUR_MONDE;) {
            filledArea = genenerDecors(objetDecorList, filledArea);
        }
    }

    public void update(double deltaTemps) {
        charlotte.update(deltaTemps, camera);
        charlotte.isDead();

        for (Ennemi ennemi:ennemis
             ) {
            // Les ennemies commencent a bouger seulement lorsque charlotte commence a bouger
            if(charlotte.isMoved())
                ennemi.update(deltaTemps,camera);

            ennemi.isDead();

            // --Detection des collisions--
            // Teste si les coords de charlotte et de l'ennemie se touchent
            // Si oui, alors charlotte perd une vie
            if(charlotte.isTouching(ennemi)) {
                ennemi.attack(charlotte);
                barreVie.update(canvas.getGraphicsContext2D(), charlotte.getVie());
            }
        }
        end();
    }

    public void draw(GraphicsContext context) {
        for (ObjetDecor objetDecor : objetDecorList) {
            if (objetDecor.isInView(camera))
                objetDecor.draw(context, camera);
        }

        charlotte.draw(context, camera);

        for (Ennemi ennemi : ennemis) {
            ennemi.draw(context, camera);
        }

        barreVie.update(context, charlotte.getVie());
    }

    public int genenerDecors(ArrayList<ObjetDecor> objetDecorList, int filledArea) {
        var x = (new Random()).nextInt(50, 100);
        objetDecorList.add(new ObjetDecor(x + filledArea, 410));
        return filledArea + x + 80;
    }

    /**
     * Créer une couleur random pour le background du monde
     * @return une classe Color
     */
    private Color generateRandomColor() {
        var rand = new Random();
        double hue = rand.nextDouble(190, 270), saturation = 0.84, brightness = 1.0;

        return Color.hsb(hue, saturation, brightness);
    }

    /** Test si le joueur a gagne
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
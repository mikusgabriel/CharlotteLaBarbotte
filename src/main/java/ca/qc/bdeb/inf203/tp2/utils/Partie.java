package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.BarreVie;
import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import ca.qc.bdeb.inf203.tp2.gameObjects.ObjetDecor;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;


public class Partie {
    private final Charlotte charlotte;
    private final BarreVie barreVie=new BarreVie();
    private final Canvas canvas;
    private final Camera camera;
    private final ArrayList<Ennemi> ennemis = new ArrayList<>();
    private final ArrayList<ObjetDecor> objetDecorList = new ArrayList<>();


    // Constructeur : on crée les objets de la partie
    public Partie(Canvas canvas, Charlotte charlotte,Camera camera, Color backgroundColor) {
        this.charlotte = charlotte;
        this.canvas = canvas;
        this.camera=camera;
        for(int i=0;i<5;i++){
            ennemis.add(new Ennemi(canvas,1));
            System.out.println("ennemi print");
        }
        barreVie.setBackgroundColor(backgroundColor);

        //generation decor au debut
        var filledArea = 0;
        while (filledArea < 4160) {
            filledArea = generateBackground(objetDecorList, filledArea);
        }
    }

    public void update(double deltaTemps) {
        charlotte.update(deltaTemps,camera);
        charlotte.isDead();

        for (Ennemi ennemi:ennemis
             ) {
            ennemi.update(deltaTemps,camera);
            ennemi.isDead();

            if(charlotte.getDroite() > ennemi.getX() && charlotte.getY() == ennemi.getY()) {
                charlotte.perdreVie();
                barreVie.update(canvas.getGraphicsContext2D(), charlotte.getVie());
            }
        }




// Tester les collisions
// Autres : vérifie si on a gagné/perdu, ...
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

    //FIXME implementation temporaire pour finir la partie
   public void end(){
        if(charlotte.getDroite() > canvas.getWidth()) {
            Platform.exit();
        }
    }

    public int generateBackground(ArrayList<ObjetDecor> objetDecorList, int filledArea) {
        var x = (new Random()).nextInt(50, 100);
        objetDecorList.add(new ObjetDecor(x + filledArea, 410));
        return filledArea + x + 80;
    }
}
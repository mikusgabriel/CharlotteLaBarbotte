package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class Partie {
    private final Charlotte charlotte;
    private final Canvas canvas;
    private final ArrayList<Ennemi> ennemis = new ArrayList<>();


    // Constructeur : on crée les objets de la partie
    public Partie(Canvas canvas, Charlotte charlotte) {
        this.charlotte = charlotte;
        this.canvas = canvas;
        for(int i=0;i<5;i++){
            ennemis.add(new Ennemi(canvas,1));
        }


    }

    public void update(double deltaTemps) {
        charlotte.update(deltaTemps);
        charlotte.isDead();
        for (Ennemi ennemi:ennemis
             ) {
            ennemi.update(deltaTemps);
            ennemi.isDead();
        }


// Tester les collisions
// Autres : vérifie si on a gagné/perdu, ...
    }

    public void draw(GraphicsContext context, Camera camera) {
        double height = canvas.getHeight();
        double width = canvas.getWidth();
        double xScreen = camera.calculerEcranX(width);
        for (Ennemi ennemi:ennemis
        ) {
            System.out.println("ennemi print");
            ennemi.draw(context);
        }
        charlotte.draw(context);
        context.setFill(Color.RED);
        context.fillRect(xScreen, height, width, height);
// Dessiner les objets
    }

    //FIXME implementation temporaire pour finir la partie
   public void end(){

    }
}